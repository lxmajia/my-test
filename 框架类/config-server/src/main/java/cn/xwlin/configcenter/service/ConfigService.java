package cn.xwlin.configcenter.service;

import cn.xwlin.configcenter.entity.AppInfo;
import cn.xwlin.configcenter.entity.ConfigInfo;
import cn.xwlin.configcenter.mapper.AppInfoMapper;
import cn.xwlin.configcenter.mapper.ConfigInfoMapper;
import cn.xwlin.configcenter.vo.resp.GetConfigData;
import cn.xwlin.configcenter.vo.resp.HttpResp;
import com.google.common.collect.Maps;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author xiang.liao
 * @create 2024/5/22
 */
@Service
public class ConfigService {
  @Autowired
  private AppInfoMapper appInfoMapper;
  @Autowired
  private ConfigInfoMapper configInfoMapper;

  public HttpResp<GetConfigData> checkAppModule(String uuid) {
    if (StringUtils.isNullOrEmpty(uuid)) {
      return HttpResp.fail(-1, "ConfigCenter:appCode or moduleCode can not be empty!");
    }
    AppInfo appInfo = appInfoMapper.selectByUuid(uuid);
    if (appInfo == null) {
      return HttpResp.fail(-1, "ConfigCenter:appCode and moduleCode is not exist!");
    }
    return HttpResp.success();
  }

  public HttpResp<GetConfigData> getAllConfig(String uuid) {
    if (StringUtils.isNullOrEmpty(uuid)) {
      return HttpResp.fail(-1, "ConfigCenter:appCode or moduleCode can not be empty!");
    }
    AppInfo appInfo = appInfoMapper.selectByUuid(uuid);
    if (appInfo == null) {
      return HttpResp.fail(-1, "ConfigCenter:appCode and moduleCode is not exist!");
    }
    List<ConfigInfo> configInfos = configInfoMapper.selectByAppModuleCode(appInfo.getAppCode(), appInfo.getModuleCode());
    GetConfigData getConfigData = new GetConfigData();
    getConfigData.setAppCode(appInfo.getAppCode());
    // 缓冲一秒的时间
    getConfigData.setNextTimeMills(System.currentTimeMillis() - 1000);
    getConfigData.setModuleCode(appInfo.getModuleCode());
    if (!CollectionUtils.isEmpty(configInfos)) {
      getConfigData.setChangeConfigMap(Maps.newHashMap());
      for (ConfigInfo info : configInfos) {
        getConfigData.getChangeConfigMap().put(info.getConfigKey(), info.getConfigValue());
      }
    }
    return HttpResp.success(getConfigData);
  }
}
