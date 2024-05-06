package cn.xwlin.timewheel.base;

import java.util.List;

public interface Timer {

  void createTimerTask(Integer taskId, List<Long> nextExecuteMills);

  void removeTimerTask(Integer taskId);

}