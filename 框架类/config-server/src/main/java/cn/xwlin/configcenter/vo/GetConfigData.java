package cn.xwlin.configcenter.vo;

/**
 * @author xiang.liao
 * @create 2024/5/22
 */
public class GetConfigData {
  private long oldVersion;
  private long newVersion;
  private String uniqueKey;
  private String configValue;

  public long getOldVersion() {
    return oldVersion;
  }

  public void setOldVersion(long oldVersion) {
    this.oldVersion = oldVersion;
  }

  public long getNewVersion() {
    return newVersion;
  }

  public void setNewVersion(long newVersion) {
    this.newVersion = newVersion;
  }

  public String getUniqueKey() {
    return uniqueKey;
  }

  public void setUniqueKey(String uniqueKey) {
    this.uniqueKey = uniqueKey;
  }

  public String getConfigValue() {
    return configValue;
  }

  public void setConfigValue(String configValue) {
    this.configValue = configValue;
  }
}
