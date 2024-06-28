package cn.xwlin.configcenter.holder;

import cn.xwlin.configcenter.entity.ConfigInfo;
import cn.xwlin.configcenter.mapper.ConfigInfoMapper;
import cn.xwlin.configcenter.dto.MyConfigCheckDTO;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xiang.liao
 * @create 2024/5/6
 */
@Component
public class ConfigCacheManager {
  private boolean initSuccess = false;
  /**
   * 具体的key=appCode#moduleCode
   * value=<configKey,modifyTime>
   */
  private static Map<String, Map<String, Long>> appModuleConfigKeyTimestampMap = new ConcurrentHashMap<>();
  @Autowired
  private ConfigInfoMapper myConfigDao;

  @PostConstruct
  public void init() {
    List<ConfigInfo> myConfigs = myConfigDao.listAllConfig();
    for (ConfigInfo myConfig : myConfigs) {
      String appModule = myConfig.getAppCode() + "$" + myConfig.getModuleCode();
      String configKey = myConfig.getConfigKey();
      long time = myConfig.getModified().getTime();
      if (!appModuleConfigKeyTimestampMap.containsKey(appModule)) {
        appModuleConfigKeyTimestampMap.put(appModule, new HashMap<>());
      }
      appModuleConfigKeyTimestampMap.get(appModule).put(configKey, time);
    }
    this.initSuccess = true;
    System.out.println("初始化成功，设置为true");
  }

  public boolean serviceOk() {
    return initSuccess;
  }

  @Scheduled(cron = "0/15 * * * * ?")
  public void schedule() {
    if (!this.initSuccess) {
      return;
    }
    // 更新配置版本(10分钟之前更新的都刷新一遍)
    long before10MinDate = System.currentTimeMillis() - (10 * 60 * 1000);
    Date date = new Date(before10MinDate);
    refreshConfig(date);
  }

  @Async
  public void refreshConfig(Date afterTime) {
    // 从数据库拉取配置
    List<ConfigInfo> myConfigs = myConfigDao.listChangeConfig(afterTime);
    for (ConfigInfo myConfig : myConfigs) {
      String appModule = myConfig.getAppCode() + "$" + myConfig.getModuleCode();
      String configKey = myConfig.getConfigKey();
      long time = myConfig.getModified().getTime();
      if (!appModuleConfigKeyTimestampMap.containsKey(appModule)) {
        appModuleConfigKeyTimestampMap.put(appModule, new HashMap<>());
      }
      appModuleConfigKeyTimestampMap.get(appModule).put(configKey, time);
    }
  }

  @Async
  public void refreshByApi(Long id) {
    // 从数据库拉取配置
    ConfigInfo myConfig = myConfigDao.selectByPrimaryKey(id);
    if(myConfig != null){
      String appModule = myConfig.getAppCode() + "$" + myConfig.getModuleCode();
      String configKey = myConfig.getConfigKey();
      long time = myConfig.getModified().getTime();
      if (!appModuleConfigKeyTimestampMap.containsKey(appModule)) {
        appModuleConfigKeyTimestampMap.put(appModule, new HashMap<>());
      }
      appModuleConfigKeyTimestampMap.get(appModule).put(configKey, time);
    }
  }

  public MyConfigCheckDTO checkConfigChange(String appCode, String moduleCode, Long fetchTime) {
    String appModule = appCode + "$" + moduleCode;
    MyConfigCheckDTO checkVO = new MyConfigCheckDTO();
    if (!appModuleConfigKeyTimestampMap.containsKey(appModule)) {
      checkVO.setNewConfigChangeCount(0);
      return checkVO;
    }
    // 设置当前时间为下次抓取时间
    long nextFetchTime = new Date().getTime();
    Map<String, Long> configKeyTimestampMap = appModuleConfigKeyTimestampMap.get(appModule);
    Set<String> changeUniqueId = Sets.newHashSet();
    for (Map.Entry<String, Long> stringLongEntry : configKeyTimestampMap.entrySet()) {
      String configKey = stringLongEntry.getKey();
      Long configKeyModifiedTime = stringLongEntry.getValue();
      if (configKeyModifiedTime > fetchTime) {
        String configUniqueId = appModule + "$" + configKey;
        changeUniqueId.add(configUniqueId);
      }
      if(configKeyModifiedTime > nextFetchTime){
        nextFetchTime = configKeyModifiedTime;
      }
    }
    checkVO.setNewConfigChangeCount(changeUniqueId.size());
    checkVO.setNextFetchTime(nextFetchTime);
    if (CollectionUtils.isEmpty(changeUniqueId)) {
      return checkVO;
    }
    List<ConfigInfo> configInfos = myConfigDao.selectByUniqueKeyList(Lists.newArrayList(changeUniqueId));
    for (ConfigInfo configInfo : configInfos) {
      checkVO.getNewConfigValueMap().put(configInfo.getConfigKey(), configInfo.getConfigValue());
    }
    return checkVO;
  }
}
