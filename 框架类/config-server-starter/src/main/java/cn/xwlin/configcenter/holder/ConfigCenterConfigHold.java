package cn.xwlin.configcenter.holder;


import cn.xwlin.configcenter.util.OSUtils;

public class ConfigCenterConfigHold {
  public static String url = "server.xwlin.cn";
  public static int port = 8899;
  public static String appCode;
  public static String moduleCode;
  public static int timeout = 60 * 1000;

  private static String version = "1.0.0";
  private static String VERIFT_APP_MODULE = "/rest/api/v1/config/checkAppModule";
  private static String GET_ALL_CONFIG = "/rest/api/v1/config/getAllConfig";
  private static String GET_SYS_CONFIG = "/rest/api/v1/config/getSysConfig";
  private static String REFRESH_CONFIG = "/rest/api/v1/config/refreshConfig";
  private static String GET_CONFIG_VALUE = "/rest/api/v1/config/getConfigValue";

  public static String getCheckAppModuleUrl() {
    boolean b = url.startsWith("http");
    return b ? "" : "http://" + url + ":" + port + VERIFT_APP_MODULE + "?appCode=" + appCode + "&" + "moduleCode=" + moduleCode+ "&ip=" + OSUtils.getLocalIP();
  }

  public static String getAllConfig() {
    boolean b = url.startsWith("http");
    return b ? "" : "http://" + url + ":" + port + GET_ALL_CONFIG + "?appCode=" + appCode + "&" + "moduleCode=" + moduleCode + "&ip=" + OSUtils.getLocalIP();
  }

  public static String getSysConfig() {
    boolean b = url.startsWith("http");
    return b ? "" : "http://" + url + ":" + port + GET_SYS_CONFIG + "?appCode=" + appCode + "&" +
            "moduleCode=" + moduleCode + "&ip=" + OSUtils.getLocalIP();
  }

  public static String getRefreshConfig() {
    boolean b = url.startsWith("http");
    return b ? "" :
            "http://" + url + ":" + port + REFRESH_CONFIG + "?appCode=" + appCode + "&" + "moduleCode=" + moduleCode + "&lastFetchTime=" + ClientConfigCacheManager.refreshTime + "&requestTimeout=" + timeout + "&ip=" + OSUtils.getLocalIP();
  }

  public static String getConfigValue(String configKey) {
    boolean b = url.startsWith("http");
    return b ? "" : "http://" + url + ":" + port + GET_CONFIG_VALUE + "?appCode=" + appCode + "&" + "moduleCode=" + moduleCode + "&configKey=" + configKey + "&requestTimeout=" + timeout + "&ip=" + OSUtils.getLocalIP();
  }
}