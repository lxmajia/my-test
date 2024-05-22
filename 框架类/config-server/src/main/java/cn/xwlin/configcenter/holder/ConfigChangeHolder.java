package cn.xwlin.configcenter.holder;

import cn.xwlin.configcenter.entity.ConfigInfo;
import cn.xwlin.configcenter.mapper.ConfigInfoMapper;
import cn.xwlin.configcenter.dto.MyConfigCheckDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiang.liao
 * @create 2024/5/6
 */
@Component
public class ConfigChangeHolder {
  private boolean initSuccess = false;

  /**
   * 具体的key=appCode#moduleCode#configKey
   */
  private static Map<String, Long> appModuleConfigKeyVersionMap = new HashMap<>();

  @Autowired
  private ConfigInfoMapper myConfigDao;

  @PostConstruct
  public void init() {
    List<ConfigInfo> myConfigs = myConfigDao.listAllConfig();
    for (ConfigInfo myConfig : myConfigs) {
      appModuleConfigKeyVersionMap.put(myConfig.getUniqueKey(), myConfig.getVersion());
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
    // 更新配置版本
    long before30MinDate = System.currentTimeMillis() - (30 * 60 * 1000);
    Date date = new Date(before30MinDate);
    // 从数据库拉取配置
    List<ConfigInfo> myConfigs = myConfigDao.listChangeConfig(date);
    for (ConfigInfo myConfig : myConfigs) {
      Long aLong = appModuleConfigKeyVersionMap.get(myConfig.getConfigKey());
      if (aLong == null || aLong < myConfig.getVersion()) {
        appModuleConfigKeyVersionMap.put(myConfig.getUniqueKey(), myConfig.getVersion());
      }
    }
  }

  public MyConfigCheckDTO checkConfigChange(String uniqueKey, Long oldVersion) {
    MyConfigCheckDTO checkVO = new MyConfigCheckDTO();
    checkVO.setUniqueKey(uniqueKey);
    checkVO.setOldVersion(oldVersion);

    Long newVersion = appModuleConfigKeyVersionMap.get(uniqueKey);
    String newValue = null;
    // 不存在，查询一下数据库
    ConfigInfo myConfig = null;
    if (newVersion == null) {
      myConfig = myConfigDao.selectByUniqueKey(uniqueKey);
      if (myConfig == null) {
        checkVO.setConfigExist(false);
        return checkVO;
      } else {
        appModuleConfigKeyVersionMap.put(myConfig.getUniqueKey(), myConfig.getVersion());
        newVersion = myConfig.getVersion();
        newValue = myConfig.getConfigValue();
      }
    }
    checkVO.setConfigExist(true);
    checkVO.setNewVersion(newVersion);

    if (newVersion > oldVersion) {
      if (newValue == null) {
        myConfig = myConfigDao.selectByUniqueKey(uniqueKey);
        newValue = myConfig.getConfigValue();
      }
      checkVO.setChange(true);
      checkVO.setNewConfigValue(newValue);
      checkVO.setModified(myConfig.getModified());
    } else {
      checkVO.setChange(false);
    }
    return checkVO;
  }
}
