package cn.xwlin.configcenter.vo.request;

/**
 * @author xiang.liao
 * @create 2024/6/26
 */
public class UpdateSysConfigReq {
  private Long id;
  private Long appModuleId;
  private String configKey;
  private String configValue;

  public Long getAppModuleId() {
    return appModuleId;
  }

  public void setAppModuleId(Long appModuleId) {
    this.appModuleId = appModuleId;
  }

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

  public String getConfigValue() {
    return configValue;
  }

  public void setConfigValue(String configValue) {
    this.configValue = configValue;
  }
}
