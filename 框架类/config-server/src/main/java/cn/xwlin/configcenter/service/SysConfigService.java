package cn.xwlin.configcenter.service;

import cn.xwlin.configcenter.entity.AppInfo;
import cn.xwlin.configcenter.entity.ConfigInfo;
import cn.xwlin.configcenter.entity.SysConfig;
import cn.xwlin.configcenter.mapper.AppInfoMapper;
import cn.xwlin.configcenter.mapper.ConfigInfoMapper;
import cn.xwlin.configcenter.mapper.SysConfigMapper;
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
public class SysConfigService {
  @Autowired
  private AppInfoMapper appInfoMapper;
  @Autowired
  private SysConfigMapper sysConfigMapper;

  public HttpResp<GetConfigData> getSysConfig(String appCode, String moduleCode) {
    if (StringUtils.isNullOrEmpty(appCode) || StringUtils.isNullOrEmpty(moduleCode)) {
      return HttpResp.fail(-1, "ConfigCenter:appCode or moduleCode can not be empty!");
    }
    AppInfo appInfo = appInfoMapper.selectByAppModule(appCode, moduleCode);
    if (appInfo == null) {
      return HttpResp.fail(-1, "ConfigCenter:appCode and moduleCode is not exist!");
    }
    List<SysConfig> sysConfigs = sysConfigMapper.listByAppModuleId(appInfo.getId());

    GetConfigData getConfigData = new GetConfigData();
    if (!CollectionUtils.isEmpty(sysConfigs)) {
      getConfigData.setSysConfigMap(Maps.newHashMap());
      for (SysConfig sysConfig : sysConfigs) {
        getConfigData.getSysConfigMap().put(sysConfig.getConfigKey(), sysConfig.getConfigValue());
      }
    }
    return HttpResp.succuess(getConfigData);
  }
}
