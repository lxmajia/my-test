package cn.xwlin.configcenter.helper;

import cn.xwlin.configcenter.config.ConfigCenterConfig;
import cn.xwlin.configcenter.holder.ClientConfigCacheManager;
import cn.xwlin.configcenter.holder.ConfigCenterConfigHold;


/**
 * @author xiang.liao
 * @create 2024/5/22
 */
public class CfgHelper {
  private ClientConfigCacheManager configManager;

  public CfgHelper(ConfigCenterConfig configCenterConfig) {
    ConfigCenterConfigHold.appCode = configCenterConfig.getAppCode();
    ConfigCenterConfigHold.moduleCode = configCenterConfig.getModuleCode();
    ConfigCenterConfigHold.url = configCenterConfig.getUrl();
    ConfigCenterConfigHold.port = configCenterConfig.getPort();
    ConfigCenterConfigHold.timeout = configCenterConfig.getTimeout();
    configManager = new ClientConfigCacheManager();
    configManager.runConfigManager();
  }

  public <T> T getConfig(Class<T> clazz) {
    T value = null;
    // if start error, lazy-loading
    if (this.configManager == null) {
      synchronized (CfgHelper.class) {
        if (this.configManager == null) {
          this.configManager = new ClientConfigCacheManager();
        }
      }
    }
    value = configManager.GetConfigValue(clazz.getSimpleName(), clazz);
    return value;
  }
}
