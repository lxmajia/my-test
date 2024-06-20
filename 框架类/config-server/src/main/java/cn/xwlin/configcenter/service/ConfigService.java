package cn.xwlin.configcenter.service;

import cn.xwlin.configcenter.entity.AppInfo;
import cn.xwlin.configcenter.entity.ConfigInfo;
import cn.xwlin.configcenter.mapper.AppInfoMapper;
import cn.xwlin.configcenter.mapper.ConfigInfoMapper;
import cn.xwlin.configcenter.vo.GetConfigData;
import cn.xwlin.configcenter.vo.HttpResp;
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

  public HttpResp<GetConfigData> checkAppModule(String appCode, String moduleCode) {
    if (StringUtils.isNullOrEmpty(appCode) || StringUtils.isNullOrEmpty(moduleCode)) {
      return HttpResp.fail(-1, "ConfigCenter:appCode or moduleCode can not be empty!");
    }
    AppInfo appInfo = appInfoMapper.selectByAppModule(appCode, moduleCode);
    if (appInfo == null) {
      return HttpResp.fail(-1, "ConfigCenter:appCode and moduleCode is not exist!");
    }
    return HttpResp.success();
  }

  public HttpResp<GetConfigData> getAllConfig(String appCode, String moduleCode) {
    if (StringUtils.isNullOrEmpty(appCode) || StringUtils.isNullOrEmpty(moduleCode)) {
      return HttpResp.fail(-1, "ConfigCenter:appCode or moduleCode can not be empty!");
    }
    AppInfo appInfo = appInfoMapper.selectByAppModule(appCode, moduleCode);
    if (appInfo == null) {
      return HttpResp.fail(-1, "ConfigCenter:appCode and moduleCode is not exist!");
    }
    List<ConfigInfo> configInfos = configInfoMapper.selectByAppModuleCode(appCode, moduleCode);
    GetConfigData getConfigData = new GetConfigData();
    getConfigData.setAppCode(appCode);
    getConfigData.setModuleCode(moduleCode);
    if (!CollectionUtils.isEmpty(configInfos)) {
      getConfigData.setChangeConfigMap(Maps.newHashMap());
      for (ConfigInfo info : configInfos) {
        getConfigData.getChangeConfigMap().put(info.getConfigKey(), info.getConfigValue());
      }
    }
    return HttpResp.success(getConfigData);
  }
}
