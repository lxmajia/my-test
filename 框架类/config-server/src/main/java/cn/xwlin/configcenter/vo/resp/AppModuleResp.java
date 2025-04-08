package cn.xwlin.configcenter.vo.resp;

/**
 * @author xiang.liao
 * @create 2024/6/20
 */
public class AppModuleResp {
  private Long id;
  private String appCode;
  private String appModule;
  private String uuid;

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getAppCode() {
    return appCode;
  }

  public void setAppCode(String appCode) {
    this.appCode = appCode;
  }

  public String getAppModule() {
    return appModule;
  }

  public void setAppModule(String appModule) {
    this.appModule = appModule;
  }
}
