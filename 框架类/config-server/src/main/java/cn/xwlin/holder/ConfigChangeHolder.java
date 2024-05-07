package cn.xwlin.holder;

import cn.xwlin.entity.MyConfig;
import cn.xwlin.mapper.MyConfigDao;
import cn.xwlin.vo.MyConfigCheckVO;
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
  private static Map<String, Long> configNameVersionMapping = new HashMap<>();

  @Autowired
  private MyConfigDao myConfigDao;

  @PostConstruct
  public void init() {
    List<MyConfig> myConfigs = myConfigDao.listAllConfig();
    for (MyConfig myConfig : myConfigs) {
      configNameVersionMapping.put(myConfig.getConfigKey(), myConfig.getVersion());
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
    List<MyConfig> myConfigs = myConfigDao.listChangeConfig(date);
    for (MyConfig myConfig : myConfigs) {
      Long aLong = configNameVersionMapping.get(myConfig.getConfigKey());
      if (aLong == null || aLong < myConfig.getVersion()) {
        configNameVersionMapping.put(myConfig.getConfigKey(), myConfig.getVersion());
      }
    }
  }

  public MyConfigCheckVO checkConfigChange(String configKey, Long oldVersion) {
    MyConfigCheckVO checkVO = new MyConfigCheckVO();
    if (!this.initSuccess) {
      checkVO.setServiceOk(false);
      return checkVO;
    }
    checkVO.setServiceOk(true);
    checkVO.setConfigKey(configKey);
    checkVO.setOldVersion(oldVersion);

    Long version = configNameVersionMapping.get(configKey);
    // 不存在，查询一下数据库
    MyConfig myConfig = null;
    if (version == null) {
      myConfig = myConfigDao.selectByConfigKey(configKey);
      if (myConfig == null) {
        checkVO.setConfigExist(false);
        return checkVO;
      } else {
        configNameVersionMapping.put(configKey, myConfig.getVersion());
        version = myConfig.getVersion();
      }
    }

    if (version > oldVersion) {
      checkVO.setConfigExist(true);
      checkVO.setChange(true);
      checkVO.setNewConfigValue(myConfig.getConfigValue());
      checkVO.setModified(myConfig.getModified());
    } else {
      checkVO.setConfigExist(true);
      checkVO.setChange(false);
    }
    checkVO.setNewVersion(version);
    return checkVO;
  }
}
