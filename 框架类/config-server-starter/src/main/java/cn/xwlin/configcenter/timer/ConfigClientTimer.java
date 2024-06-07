package cn.xwlin.configcenter.timer;

import cn.xwlin.configcenter.holder.ConfigCenterConfigHold;

import java.util.Timer;

/**
 * Created by minji on 15/11/16.
 */
public class ConfigClientTimer {

  private static Timer timer;
  private static final Object obj = new Object();

  public static void TimerInstanceStart(ConfigFetchTimerTask timerTask) {
    if (null == timer) {
      synchronized (obj) {
        if (null == timer) {
          timer = new Timer("ConfigCenter-fetch_timer", true);
          long timerPeriod = ConfigCenterConfigHold.timeout;
          if (timerPeriod < 10000) {
            timerPeriod = 10000;
          }
          if (timerPeriod > 60000) {
            timerPeriod = 60;
          }
          timer.scheduleAtFixedRate(timerTask, 0, timerPeriod);
        }
      }
    }
  }
}
