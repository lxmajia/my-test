package cn.xwlin.configcenter.helper;

import cn.xwlin.configcenter.holder.ConfigCacheManeger;
import cn.xwlin.configcenter.timer.ConfigClientTimer;
import cn.xwlin.configcenter.timer.ConfigFetchTimerTask;


/**
 * @author xiang.liao
 * @create 2024/5/22
 */
public class CfgHelper {
  private ConfigCacheManeger configHelper;
  private ConfigFetchTimerTask configFetchTimerTask;

  public CfgHelper() {
    configFetchTimerTask = ConfigFetchTimerTask.getConfigFetchTimerTaskInstance();
  }

  public <T> T getConfig(Class<T> clazz) {
    T value = null;
    // if start error, lazy-loading
    if (this.configHelper == null) {
      synchronized (CfgHelper.class) {
        if (this.configHelper == null) {
          this.configHelper = new ConfigCacheManeger();
        }
      }
    }
    value = configHelper.GetConfigValue(clazz.getSimpleName(), clazz);
    return value;
  }

  private void GetConfigTimerStart() {
    ConfigClientTimer.TimerInstanceStart(configFetchTimerTask, 60 * 1000);
  }
}
