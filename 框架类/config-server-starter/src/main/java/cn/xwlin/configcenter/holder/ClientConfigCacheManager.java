package cn.xwlin.configcenter.holder;

import cn.xwlin.configcenter.refresh.IWlinConfigChangeCallBack;
import cn.xwlin.configcenter.timer.ConfigFetchNetwork;
import cn.xwlin.configcenter.timer.ConfigFetchTimerTask;
import cn.xwlin.configcenter.vo.GetConfigData;
import cn.xwlin.configcenter.vo.HttpResp;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * @author xiang.liao
 * @create 2024/5/22
 */
public class ClientConfigCacheManager {
  public static Long refreshTime;
  private static Map<String, ConfigCacheVO> configCacheMap = new HashMap<>();
  private static Map<String, List<IWlinConfigChangeCallBack>> configItemChangeCallBackMap = new HashMap<>();

  public ClientConfigCacheManager() {
  }

  public <T> T GetConfigValue(String key, Class<T> clazz) {
    return GetConfigValueFromCacheMap(key, clazz, null);
  }

  public String GetConfigValueString(String key) {
    if (null == key || key.trim().isEmpty()) {
      return null;
    }
    if (configCacheMap.containsKey(key.trim())) {
      return configCacheMap.get(key.trim()).getConfigValue();
    }
    return null;
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
    // 先获取所有配置(这里不去校验参数对不对，在获取系统参数的时候就校验了)
    initAllConfig();

    // 定时任务Task
    Thread thread = new Thread(new ConfigFetchTimerTask());
    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
    scheduledExecutorService.scheduleAtFixedRate(thread, 10, 10, TimeUnit.SECONDS);
  }

  public void initAllConfig() {
    // firstFetchAllConfig
    String allConfig = ConfigFetchNetwork.getAllConfig();
    if (allConfig != null) {
      TypeReference<HttpResp<GetConfigData>> typeReference = new TypeReference<HttpResp<GetConfigData>>() {
      };
      HttpResp<GetConfigData> getConfigDataHttpResp = JSONObject.parseObject(allConfig, typeReference);
      if (getConfigDataHttpResp != null && getConfigDataHttpResp.getBody() != null) {
        ClientConfigCacheManager.refreshTime = getConfigDataHttpResp.getBody().getNextTimeMills();
        refreshCacheMap(getConfigDataHttpResp.getBody(), false);
      }
    }
  }


  public static void refreshCacheMap(GetConfigData getConfigData, boolean isNeedRefresh) {
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
      if (isNeedRefresh) {
        // 执行完了之后，调一下回调，动态刷新Bean
        for (Map.Entry<String, String> stringStringEntry : getConfigData.getChangeConfigMap().entrySet()) {
          customConfigItemChangeCallBack(stringStringEntry.getKey().trim());
        }
      }
    }
  }

  private static void customConfigItemChangeCallBack(String key) {
    if (!StringUtils.hasLength(key) || CollectionUtils.isEmpty(configItemChangeCallBackMap)) {
      return;
    }
    List<IWlinConfigChangeCallBack> iWlinConfigChangeCallBacks = configItemChangeCallBackMap.get(key);
    if (CollectionUtils.isEmpty(iWlinConfigChangeCallBacks)) {
      return;
    }
    for (IWlinConfigChangeCallBack iConfigItemChangeCallBack : iWlinConfigChangeCallBacks) {
      iConfigItemChangeCallBack.configModifyCallBack(key);
    }
  }

  public void setCustomConfigChangeCallBack(String key, IWlinConfigChangeCallBack configChangeCallBack) {
    // 保证线程
    if (configChangeCallBack != null) {
      List<IWlinConfigChangeCallBack> iWlinConfigChangeCallBacks = configItemChangeCallBackMap.get(key);
      if (CollectionUtils.isEmpty(iWlinConfigChangeCallBacks)) {
        synchronized (ClientConfigCacheManager.class) {
          if (CollectionUtils.isEmpty(iWlinConfigChangeCallBacks)) {
            iWlinConfigChangeCallBacks = new ArrayList<>();
            iWlinConfigChangeCallBacks.add(configChangeCallBack);
            configItemChangeCallBackMap.put(key, iWlinConfigChangeCallBacks);
          }
        }
      } else {
        iWlinConfigChangeCallBacks.add(configChangeCallBack);
      }
    }
  }
}
