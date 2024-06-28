package cn.xwlin.configcenter.holder;

import cn.xwlin.configcenter.timer.ConfigFetchNetwork;
import cn.xwlin.configcenter.timer.ConfigFetchTimerTask;
import cn.xwlin.configcenter.vo.GetConfigData;
import cn.xwlin.configcenter.vo.HttpResp;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiang.liao
 * @create 2024/5/22
 */
public class ClientConfigCacheManager {
  public static Long refreshTime;
  private static Map<String, ConfigCacheVO> configCacheMap = new HashMap<>();

  public ClientConfigCacheManager() {
  }

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

  public void runConfigManager() {
    ClientConfigCacheManager.refreshTime = System.currentTimeMillis();
    // 先获取所有配置(这里不去校验参数对不对，在获取系统参数的时候就校验了)
    initAllConfig();

    // 定时任务Task
    Thread thread = new Thread(new ConfigFetchTimerTask());
    thread.start();
  }

  public void initAllConfig() {
    // firstFetchAllConfig
    String allConfig = ConfigFetchNetwork.getAllConfig();
    if (allConfig != null) {
      TypeReference<HttpResp<GetConfigData>> typeReference = new TypeReference<HttpResp<GetConfigData>>() {
      };
      HttpResp<GetConfigData> getConfigDataHttpResp = JSONObject.parseObject(allConfig, typeReference);
      if (getConfigDataHttpResp != null && getConfigDataHttpResp.getBody() != null) {
        refreshCacheMap(getConfigDataHttpResp.getBody());
      }
    }
  }


  public static void refreshCacheMap(GetConfigData getConfigData) {
    if (getConfigData == null) {
      return;
    }
    if (!CollectionUtils.isEmpty(getConfigData.getChangeConfigMap())) {
      for (Map.Entry<String, String> stringStringEntry : getConfigData.getChangeConfigMap().entrySet()) {
        ConfigCacheVO configCacheVO = new ConfigCacheVO();
        configCacheVO.setKey(stringStringEntry.getKey().trim());
        configCacheVO.setConfigValue(stringStringEntry.getValue());
        configCacheMap.put(stringStringEntry.getKey().trim(), configCacheVO);
      }
    }
  }
}
