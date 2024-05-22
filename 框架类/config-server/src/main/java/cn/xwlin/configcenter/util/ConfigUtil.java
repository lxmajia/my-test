package cn.xwlin.configcenter.util;

/**
 * @author xiang.liao
 * @create 2024/5/22
 */
public class ConfigUtil {
  public static String getConfigUniqueKey(String appCode, String moduleCode, String configKey) {
    return appCode + "#" + moduleCode + "#" + configKey;
  }
}
