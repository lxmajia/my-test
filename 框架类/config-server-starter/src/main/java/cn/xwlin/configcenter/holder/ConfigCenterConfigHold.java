package cn.xwlin.configcenter.holder;


import cn.xwlin.configcenter.util.OSUtils;

public class ConfigCenterConfigHold {
  public static String url;
  public static int port;
  public static String uuid;
  public static int timeout = 60 * 1000;
  private static String version = "1.0.0";
  private static String VERIFT_APP_MODULE = "/rest/api/v1/config/checkAppModule";
  private static String GET_ALL_CONFIG = "/rest/api/v1/config/getAllConfig";
  private static String GET_SYS_CONFIG = "/rest/api/v1/config/getSysConfig";
  private static String REFRESH_CONFIG = "/rest/api/v1/config/refreshConfig";
  private static String GET_CONFIG_VALUE = "/rest/api/v1/config/getConfigValue";

  public static String getCheckAppModuleUrl() {
    boolean b = url.startsWith("http");
    String requestUrl = url + ":" + port + VERIFT_APP_MODULE + "?uuid=" + uuid + "&ip=" + OSUtils.getLocalIP();
    return b ? requestUrl : "http://" + requestUrl;
  }

  public static String getAllConfig() {
    boolean b = url.startsWith("http");
    String requestUrl = url + ":" + port + GET_ALL_CONFIG + "?uuid=" + uuid + "&ip=" + OSUtils.getLocalIP();
    return b ? requestUrl : "http://" + requestUrl;
  }

  public static String getSysConfig() {
    boolean b = url.startsWith("http");
    String requestUrl = url + ":" + port + GET_SYS_CONFIG + "?uuid=" + uuid + "&ip=" + OSUtils.getLocalIP();
    return b ? requestUrl : "http://" + requestUrl;
  }

  public static String getRefreshConfig() {
    boolean b = url.startsWith("http");
    String requestUrl = url + ":" + port + REFRESH_CONFIG + "?uuid=" + uuid + "&lastFetchTime=" + ClientConfigCacheManager.refreshTime + "&requestTimeout=" + timeout + "&ip=" + OSUtils.getLocalIP();
    return b ? requestUrl : "http://" + requestUrl;
  }

  public static String getConfigValue(String configKey) {
    boolean b = url.startsWith("http");
    String requestUrl = url + ":" + port + GET_CONFIG_VALUE + "?uuid=" + uuid + "&configKey=" + configKey + "&requestTimeout=" + timeout + "&ip=" + OSUtils.getLocalIP();
    return b ? requestUrl : "http://" + requestUrl;
  }
}