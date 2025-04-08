package cn.xwlin.configcenter.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.xwlin.configcenter.dto.MyConfigCheckDTO;
import cn.xwlin.configcenter.holder.ConfigCacheManager;
import cn.xwlin.configcenter.service.ConfigService;
import cn.xwlin.configcenter.service.SysConfigService;
import cn.xwlin.configcenter.vo.resp.GetConfigData;
import cn.xwlin.configcenter.vo.resp.HttpResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author xiang.liao
 * @create 2024/5/6
 */
@RestController
@RequestMapping("/rest/api/v1/config")
public class ConfigCenterApiController {

  @Autowired
  private ConfigService configService;
  @Autowired
  private SysConfigService sysConfigService;
  @Autowired
  private ConfigCacheManager configCacheManager;

  @RequestMapping("/checkAppModule")
  @SaIgnore
  public HttpResp checkAppModule(String uuid) {
    return configService.checkAppModule(uuid);
  }

  @RequestMapping("/getSysConfig")
  @SaIgnore
  public HttpResp<GetConfigData> getSysConfig(String uuid) {
    return sysConfigService.getSysConfig(uuid);
  }

  @RequestMapping("/getAllConfig")
  @SaIgnore
  public HttpResp<GetConfigData> getAllConfig(String uuid) {
    return configService.getAllConfig(uuid);
  }

  @RequestMapping("/refreshConfig")
  @SaIgnore
  public HttpResp<GetConfigData> sayHello(String uuid, String ip, long lastFetchTime, Long requestTimeout) {
    MyConfigCheckDTO checkVO = configCacheManager.checkConfigChange(uuid, lastFetchTime);
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
