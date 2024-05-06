package cn.xwlin.tio;

import cn.xwlin.tio.task.TaskContext;
import cn.xwlin.tio.task.TaskService;
import cn.xwlin.tio.thread.ReportExecuteStatusThread;
import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.threads.TaskThreadFactory;
import org.tio.client.ClientChannelContext;
import org.tio.client.ClientTioConfig;
import org.tio.client.ReconnConf;
import org.tio.client.TioClient;
import org.tio.client.intf.ClientAioHandler;
import org.tio.client.intf.ClientAioListener;
import org.tio.core.Node;
import org.tio.core.Tio;

import java.util.*;
import java.util.concurrent.*;

/**
 * @author MaJiaXueYuan.lx
 * @ClassName MyTaskChannel.java
 * @createTime 2022-12-8-0008
 */
@Getter
@Setter
@Slf4j
public class TaskScheduleStarter {

    //服务器节点
    public static Node serverNode = new Node(Const.SERVER, Const.PORT);
    //事件监听器，可以为null，但建议自己实现该接口，可以参考showcase了解些接口
    public static ClientAioListener aioListener = null;
    //断链后自动连接的，不想自动连接请设为null
    private static ReconnConf reconnConf = new ReconnConf(5000L);
    //一组连接共用的上下文对象
    public static ClientTioConfig clientTioConfig = null;
    public static TioClient tioClient = null;
    private static ClientChannelContext clientChannelContext;
    private String appId;
    private ClientAioHandler tioClientHandler;

    private Set<TaskService> taskServices;

    public TaskScheduleStarter(String appId, ClientAioHandler tioClientHandler, Set<TaskService> taskServices) {
        this.appId = appId;
        this.tioClientHandler = tioClientHandler;
        this.taskServices = taskServices;
    }

    public void start() {
        log.info("LX_TASK start!!!!!");
        Map<String, TaskService> taskMap = new HashMap<>();
        TaskService task;
        String className;
        for (Iterator oneTask = this.taskServices.iterator(); oneTask.hasNext(); taskMap.put(className, task)) {
            task = (TaskService) oneTask.next();
            className = task.getClass().getName();
            if (className.indexOf("$$EnhancerBySpringCGLIB") > 0) {
                className = className.substring(0, className.indexOf("$$EnhancerBySpringCGLIB"));
            }
        }

        log.info("保存的任务信息:{}", JSON.toJSONString(taskMap.keySet()));

        clientTioConfig = new ClientTioConfig(this.tioClientHandler, aioListener, reconnConf);
        clientTioConfig.setHeartbeatTimeout(Const.TIMEOUT);
        try {
            tioClient = new TioClient(clientTioConfig);
            clientChannelContext = tioClient.connect(serverNode);

            ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1, new TaskThreadFactory("DDC-ScheduledThreadPool-Thread", true, 1));
            scheduledExecutorService.scheduleAtFixedRate(new ReportExecuteStatusThread(), 3L, 5L, TimeUnit.SECONDS);

            send();
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException("LX_TASK LINK FAILED！");
        }


        TaskContext.setAppId(this.appId);
        TaskContext.setClientChannelContext(clientChannelContext);
        TaskContext.setTaskMap(taskMap);
    }

    private static void send() throws Exception {
        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setTaskType("1001");
        taskInfo.setClassName("per.lx.handler.task.FixDataJob");
        taskInfo.setTaskParam("o1,o2,o3,o4,o5____LINK SUCCESS");
        taskInfo.setUtcTime(System.currentTimeMillis());

        String tJson = JSON.toJSONString(taskInfo);
        LlJobPacket packet = new LlJobPacket();
        packet.setBody(tJson.getBytes(LlJobPacket.CHARSET));
        Tio.send(clientChannelContext, packet);
    }

}
