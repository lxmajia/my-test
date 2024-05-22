package cn.xwlin.configcenter.timer;


import java.util.TimerTask;

/**
 * Created by minji on 15/11/16.
 */
public class ConfigFetchTimerTask extends TimerTask {

  private static ConfigFetchTimerTask hotSwitchTimerTask;

  private static final Object lock =new Object();

  public static ConfigFetchTimerTask getConfigFetchTimerTaskInstance()
  {
    if(null==hotSwitchTimerTask)
    {
      synchronized (lock)
      {
        if(null==hotSwitchTimerTask)
        {
          hotSwitchTimerTask=new ConfigFetchTimerTask();
        }
      }
    }
    return hotSwitchTimerTask;
  }

  @Override
  public void run() {
    // 拉取配置，并更新缓存
  }
}
