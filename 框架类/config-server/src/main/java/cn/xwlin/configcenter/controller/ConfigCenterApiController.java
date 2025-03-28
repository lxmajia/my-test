package cn.xwlin.configcenter.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.xwlin.configcenter.dto.MyConfigCheckDTO;
import cn.xwlin.configcenter.holder.ConfigCacheManager;
import cn.xwlin.configcenter.holder.ConfigChangeRequestHolder;
import cn.xwlin.configcenter.service.ConfigService;
import cn.xwlin.configcenter.service.SysConfigService;
import cn.xwlin.configcenter.vo.resp.GetConfigData;
import cn.xwlin.configcenter.vo.resp.HttpResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Date;


/**
 * @author xiang.liao
 * @create 2024/5/6
 */
@RestController
@RequestMapping("/rest/api/v1/config")
public class ConfigCenterApiController {

  @Autowired
  private ConfigChangeRequestHolder configChangeRequestHolder;
  @Autowired
  private ConfigService configService;
  @Autowired
  private SysConfigService sysConfigService;
  @Autowired
  private ConfigCacheManager configCacheManager;

  @RequestMapping("/checkAppModule")
  @SaIgnore
  public HttpResp checkAppModule(String appCode, String moduleCode) {
    return configService.checkAppModule(appCode, moduleCode);
  }

  @RequestMapping("/getSysConfig")
  @SaIgnore
  public HttpResp<GetConfigData> getSysConfig(String appCode, String moduleCode) {
    return sysConfigService.getSysConfig(appCode, moduleCode);
  }

  @RequestMapping("/getAllConfig")
  @SaIgnore
  public HttpResp<GetConfigData> getAllConfig(String appCode, String moduleCode) {
    return configService.getAllConfig(appCode, moduleCode);
  }

  @RequestMapping("/refreshConfig")
  @SaIgnore
  public HttpResp<GetConfigData> sayHello(String appCode, String moduleCode, String ip, long lastFetchTime, Long requestTimeout) {
    MyConfigCheckDTO checkVO = configCacheManager.checkConfigChange(appCode, moduleCode, lastFetchTime);
    if (checkVO.getNewConfigChangeCount() == 0) {
      // 配置不存在或者没更新，等待下次执行
      return HttpResp.success();
    }
    GetConfigData getConfigData = new GetConfigData();
    getConfigData.setNextTimeMills(checkVO.getNextFetchTime());
    getConfigData.setChangeCount(checkVO.getNewConfigChangeCount());
    getConfigData.setChangeConfigMap(checkVO.getNewConfigValueMap());
    return HttpResp.success(getConfigData);
  }
}
