package cn.xwlin.timewheel;

import cn.xwlin.tio.TioHelper;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import cn.xwlin.timewheel.base.SysCons;
import cn.xwlin.timewheel.base.Timer;
import cn.xwlin.timewheel.base.WheelDataPresist;
import cn.xwlin.cons.MyDdcStatus;
import cn.xwlin.entity.MyDdc;
import cn.xwlin.service.MyDdcService;
import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class TimeWheel implements Timer {

    private ThreadPoolExecutor executor = new ThreadPoolExecutor(20, 20, 1, TimeUnit.HOURS,
            new LinkedBlockingQueue<>(), new TimeWheelThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());

    // 默认时间轮一格的时长
    private final long gapMills = 1000L;
    // 时间轮旋转线程
    private Thread timeWheelThread = new Thread(new TimeWheelTrigger());
    // 时间轮数据
    private WheelDataPresist wheelDataPresist;
    private boolean started = false;

    @Autowired
    private TioHelper tioHelper;
    @Autowired
    private MyDdcService myDdcService;


    public TimeWheel() {
        this.wheelDataPresist = new MemoryWheelDataPresist();
    }

    @PostConstruct
    private void start() {
        if (started) {
            return;
        }
        try {
            timeWheelThread.start();
            started = true;
        } catch (Throwable t) {
            started = false;
        }
    }

    @Override
    public void createTimerTask(Integer taskId, List<Long> nextExecuteMills) {
        wheelDataPresist.addTask(taskId, nextExecuteMills);
    }

    @Override
    public void removeTimerTask(Integer taskId) {
        wheelDataPresist.deleteTask(taskId);
    }


    //处理时间轮的转动
    class TimeWheelTrigger implements Runnable {
        //记录tick的次数(和系统初识时间(固定值)相比，运行了多少秒了)
        private long hitTimes;

        public TimeWheelTrigger() {
            hitTimes = ((System.currentTimeMillis() - SysCons.SYSTEM_CREATE_TIME) / 1000);
        }

        @Override
        public void run() {
            System.out.println("#TimeWheel#Worker 启动完成..........");
            while (true) {
                waitNextHit();
                String taskKey = wheelDataPresist.getTaskKey(hitTimes);
                Set<Integer> excuteTaskIds = wheelDataPresist.getExecuteTaskId(taskKey);

                log.info("#ScanTaskKey#{}#{}", taskKey, JSON.toJSONString(excuteTaskIds));
                //处理阻塞队列中的task（这里应该用线程池去处理）
                if (!CollectionUtils.isEmpty(excuteTaskIds)) {
                    doHandleQueueTask(taskKey, excuteTaskIds);
                }
                // 删除成功的key
                wheelDataPresist.over(taskKey);
                hitTimes++;
            }
        }

        private void waitNextHit() {
            // 应该间隔的毫秒数
            long shouldtGapMills = gapMills * hitTimes;
            for (; ; ) {
                // 已经间隔的毫秒数
                long nowToSystemMills = System.currentTimeMillis() - SysCons.SYSTEM_CREATE_TIME;
                //  应该间隔的毫秒数 > 已经间隔的毫秒数，那间隔时间还不够
                long sleepTime = shouldtGapMills - nowToSystemMills;
                if (sleepTime <= 0) {
                    return;
                }
                try {
                    Thread.sleep(sleepTime);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


        private void doHandleQueueTask(String key, Set<Integer> taskId) {
            taskId.forEach(tId -> {
                MyDdc byId = myDdcService.findById(tId);
                if (byId != null && MyDdcStatus.OPEN.getValue() == byId.getStatus()) {
                    // 如果打开
                    for (int i = 1; i < 6; i++) {
                        boolean b = tioHelper.sendHandler(byId.getProjectId(), byId.getAppId(), byId.getParams());
                        if (b) {
                            break;
                        }
                        // 如果发送失败，那么这里会重试
                        try {
                            Thread.sleep(i * 100);
                        } catch (InterruptedException ignored) {
                        }
                    }
                }
            });
        }
    }
}