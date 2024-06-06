package cn.xwlin.configcenter.holder;


/**
 * @author xiang.liao
 * @create 2024/5/22
 */
public class ConfigCacheVO {
  private String key;
  private String configValue;

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getConfigValue() {
    return configValue;
  }

  public void setConfigValue(String configValue) {
    this.configValue = configValue;
  }
}
