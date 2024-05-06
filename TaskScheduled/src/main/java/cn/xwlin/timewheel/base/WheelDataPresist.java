package cn.xwlin.timewheel.base;

import java.util.List;
import java.util.Set;

public interface WheelDataPresist {

  void addTask(Integer taskId, List<Long> nextExecuteMills);

  void deleteTask(Integer taskId);

  Set<Integer> getExecuteTaskId(String key);

  void over(String key);

  // 计算cycleCount和slot
  default String getTaskKey(long hitTimes) {
    int round = (int) (hitTimes / SysCons.SLOT);
    int second = (int) (hitTimes % SysCons.SLOT);
    return SysCons.KEY_PREFIX + round + "_" + second;
  }
}
