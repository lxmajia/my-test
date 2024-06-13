package cn.xwlin.configcenter.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@ConfigurationProperties(prefix = "wl.app")
public class ConfigCenterConfig {
  private String url = "server.xwlin.cn";
  private int port = 8899;
  private String appCode;
  private String moduleCode;
  private int refreshConfigTimeout = 60 * 1000;

  public int getRefreshConfigTimeout() {
    return refreshConfigTimeout;
  }

  public void setRefreshConfigTimeout(int refreshConfigTimeout) {
    this.refreshConfigTimeout = refreshConfigTimeout;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public String getAppCode() {
    return appCode;
  }

  public void setAppCode(String appCode) {
    this.appCode = appCode;
  }

  public String getModuleCode() {
    return moduleCode;
  }

  public void setModuleCode(String moduleCode) {
    this.moduleCode = moduleCode;
  }
}