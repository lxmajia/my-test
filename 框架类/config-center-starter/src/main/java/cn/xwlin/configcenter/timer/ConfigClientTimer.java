package cn.xwlin.configcenter.timer;

import java.util.Timer;

/**
 * Created by minji on 15/11/16.
 */
public class ConfigClientTimer {

  private static Timer timer;
  private static final Object obj = new Object();

  private ConfigClientTimer() {

  }

  public static void TimerInstanceStart(ConfigFetchTimerTask timerTask,long timerPeriod) {
    if (null == timer) {
      synchronized (obj) {
        if (null == timer) {
          timer = new Timer("ConfigCenter-fetch_timer", true);
          if (timerPeriod < 10000) {
            timerPeriod = 10000;
          }
          timer.scheduleAtFixedRate(timerTask, 0, timerPeriod);
        }
      }
    }
  }
}
