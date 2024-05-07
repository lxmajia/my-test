package cn.xwlin.vo;

import java.util.Date;

/**
 * @author xiang.liao
 * @create 2024/5/6
 */
public class MyConfigCheckVO {
  private boolean serviceOk;
  private boolean configExist;
  private String configKey;
  private Long oldVersion;
  private Long newVersion;
  private boolean isChange;
  private String newConfigValue;
  private Date modified;

  public boolean isConfigExist() {
    return configExist;
  }

  public void setConfigExist(boolean configExist) {
    this.configExist = configExist;
  }

  public boolean isServiceOk() {
    return serviceOk;
  }

  public void setServiceOk(boolean serviceOk) {
    this.serviceOk = serviceOk;
  }

  public String getConfigKey() {
    return configKey;
  }

  public void setConfigKey(String configKey) {
    this.configKey = configKey;
  }

  public Long getOldVersion() {
    return oldVersion;
  }

  public void setOldVersion(Long oldVersion) {
    this.oldVersion = oldVersion;
  }

  public Long getNewVersion() {
    return newVersion;
  }

  public void setNewVersion(Long newVersion) {
    this.newVersion = newVersion;
  }

  public boolean isChange() {
    return isChange;
  }

  public void setChange(boolean change) {
    isChange = change;
  }

  public String getNewConfigValue() {
    return newConfigValue;
  }

  public void setNewConfigValue(String newConfigValue) {
    this.newConfigValue = newConfigValue;
  }

  public Date getModified() {
    return modified;
  }

  public void setModified(Date modified) {
    this.modified = modified;
  }
}
