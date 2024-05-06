package cn.xwlin.timewheel;

import com.google.common.collect.Sets;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import cn.xwlin.timewheel.base.SysCons;
import cn.xwlin.timewheel.base.WheelDataPresist;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MemoryWheelDataPresist implements WheelDataPresist {
    // key=圈数-秒数，value=需要执行的任务ID列表
    private ConcurrentHashMap<String, Set<Integer>> roundTaskIdSetMap;

    public MemoryWheelDataPresist() {
        roundTaskIdSetMap = new ConcurrentHashMap<>();
    }

    @Override
    public void addTask(Integer taskId, List<Long> nextExecuteMills) {
        long nowTimeMills = System.currentTimeMillis();
        Set<String> taskKey = Sets.newHashSet();
        for (Long nextExecuteMill : nextExecuteMills) {
            if (nextExecuteMill <= nowTimeMills) {
                continue;
            }
            long gapSecond = (nextExecuteMill - SysCons.SYSTEM_CREATE_TIME) / 1000;
            String taskKey1 = getTaskKey(gapSecond);
            taskKey.add(taskKey1);
        }

        for (String key : taskKey) {
            Set<Integer> longs = this.roundTaskIdSetMap.get(key);
            if (longs == null) {
                longs = new HashSet<>();
            }
            longs.add(taskId);
            this.roundTaskIdSetMap.put(key, longs);
        }
    }

    @Override
    public void deleteTask(Integer taskId) {
        ConcurrentHashMap.KeySetView<String, Set<Integer>> strings = this.roundTaskIdSetMap.keySet();
        for (String key : strings) {
            Set<Integer> longs = this.roundTaskIdSetMap.get(key);
            longs.remove(taskId);
            this.roundTaskIdSetMap.put(key, longs);
        }
    }

    @Override
    public Set<Integer> getExecuteTaskId(String key) {
        Set<Integer> excuteTaskIds = roundTaskIdSetMap.get(key);
        return CollectionUtils.isEmpty(excuteTaskIds) ? Sets.newHashSet() : excuteTaskIds;
    }

    @Override
    public void over(String key) {
        roundTaskIdSetMap.remove(key);
    }
}
