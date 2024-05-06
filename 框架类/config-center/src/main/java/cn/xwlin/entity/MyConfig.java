package cn.xwlin.entity;

import java.util.Date;

/**
 * @author xiang.liao
 * @create 2024/5/6
 */
public class MyConfig {
  private Long id;
  private String configKey;
  private String configType;
  private String configValue;
  private Long version;
  private Date modified;
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getConfigKey() {
    return configKey;
  }

  public void setConfigKey(String configKey) {
    this.configKey = configKey;
  }

  public String getConfigType() {
    return configType;
  }

  public void setConfigType(String configType) {
    this.configType = configType;
  }

  public String getConfigValue() {
    return configValue;
  }

  public void setConfigValue(String configValue) {
    this.configValue = configValue;
  }

  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  public Date getModified() {
    return modified;
  }

  public void setModified(Date modified) {
    this.modified = modified;
  }
}
