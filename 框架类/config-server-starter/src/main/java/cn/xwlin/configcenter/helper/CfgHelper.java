package cn.xwlin.configcenter.helper;

import cn.xwlin.configcenter.config.ConfigCenterConfig;
import cn.xwlin.configcenter.holder.ConfigCacheManeger;
import cn.xwlin.configcenter.holder.ConfigCenterConfigHold;
import cn.xwlin.configcenter.timer.ConfigClientTimer;
import cn.xwlin.configcenter.timer.ConfigFetchTimerTask;


/**
 * @author xiang.liao
 * @create 2024/5/22
 */
public class CfgHelper {
  private ConfigCacheManeger configManager;
  private ConfigFetchTimerTask configFetchTimerTask;

  public CfgHelper(ConfigCenterConfig configCenterConfig) {
    configManager = new ConfigCacheManeger();
    configManager.runConfigManager();
    ConfigCenterConfigHold.appCode = configCenterConfig.getAppCode();
    ConfigCenterConfigHold.moduleCode = configCenterConfig.getModuleCode();
    ConfigCenterConfigHold.url = configCenterConfig.getUrl();
    ConfigCenterConfigHold.port = configCenterConfig.getPort();
    ConfigCenterConfigHold.timeout = configCenterConfig.getTimeout();
  }

  public <T> T getConfig(Class<T> clazz) {
    T value = null;
    // if start error, lazy-loading
    if (this.configManager == null) {
      synchronized (CfgHelper.class) {
        if (this.configManager == null) {
          this.configManager = new ConfigCacheManeger();
        }
      }
    }
    value = configManager.GetConfigValue(clazz.getSimpleName(), clazz);
    return value;
  }
}
