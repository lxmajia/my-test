package cn.xwlin.configcenter.dto;

import java.util.Date;

/**
 * @author xiang.liao
 * @create 2024/5/6
 */
public class MyConfigCheckDTO {
  private boolean configExist;
  private String uniqueKey;
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

  public String getUniqueKey() {
    return uniqueKey;
  }

  public void setUniqueKey(String uniqueKey) {
    this.uniqueKey = uniqueKey;
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
