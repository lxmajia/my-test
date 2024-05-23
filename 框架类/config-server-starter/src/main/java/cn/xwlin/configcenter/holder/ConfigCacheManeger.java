package cn.xwlin.configcenter.holder;

import com.alibaba.fastjson2.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiang.liao
 * @create 2024/5/22
 */
public class ConfigCacheManeger {
  private String appCode;
  private String moduleCode;
  private static Map<String, ConfigCacheVO> configCacheMap = new HashMap<>();

  public <T> T GetConfigValue(String key, Class<T> clazz) {
    return GetConfigValueFromCacheMap(key, clazz, null);
  }

  private <T> T GetConfigValueFromCacheMap(String key, Class<T> clazz, T defaultValue) {
    if (null == key || key.trim().isEmpty()) return defaultValue;
    if (configCacheMap.containsKey(key.trim())) {
      T objectValue;
      String value = configCacheMap.get(key.trim()).getConfigValue();
      if (value != null) {
        objectValue = JSONObject.parseObject(configCacheMap.get(key.trim()).getConfigValue(), clazz);
        return objectValue;
      } else {
        return defaultValue;
      }
    }
    return defaultValue;
  }

  public static void addOrRefreshCacheMap(String key, String value, Long version) {
    if (null == key || key.trim().isEmpty()) {
      return;
    }
    ConfigCacheVO configCacheVO = new ConfigCacheVO();
    configCacheVO.setVersion(version);
    configCacheVO.setKey(key.trim());
    configCacheVO.setConfigValue(value);
    configCacheMap.put(key.trim(), configCacheVO);
  }
}
