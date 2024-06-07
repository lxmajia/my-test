package cn.xwlin.configcenter.controller;

import cn.xwlin.configcenter.holder.ConfigChangeRequestHolder;
import cn.xwlin.configcenter.service.ConfigService;
import cn.xwlin.configcenter.vo.GetConfigData;
import cn.xwlin.configcenter.vo.HttpResp;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;


/**
 * @author xiang.liao
 * @create 2024/5/6
 */
@RestController
@RequestMapping("/config")
public class ConfigCenterController {

  @Autowired
  private ConfigChangeRequestHolder configChangeRequestHolder;
  @Autowired
  private ConfigService configService;

  @RequestMapping("/checkAppModule")
  public HttpResp checkAppModule(String appCode, String moduleCode) {
    return configService.checkAppModule(appCode, moduleCode);
  }

  @RequestMapping("/getAllConfig")
  public HttpResp<GetConfigData> getAllConfig(String appCode, String moduleCode) {
    return configService.getAllConfig(appCode, moduleCode);
  }

  @RequestMapping("/getConfigValue")
  public HttpResp<GetConfigData> sayHello(String appCode, String moduleCode, String configKey) {
    return configService.getConfigValue(appCode, moduleCode, configKey);
  }

  @RequestMapping("/refreshConfig")
  public DeferredResult<HttpResp<GetConfigData>> sayHello(String appCode, String moduleCode, String ip, long lastFetchTime, Long requestTimeout) {
    DeferredResult<HttpResp<GetConfigData>> result = new DeferredResult<>(requestTimeout - 5000);
    configChangeRequestHolder.addHolder(appCode, moduleCode, ip, lastFetchTime, result);
    result.onTimeout(() -> {
      result.setResult(HttpResp.succuess());
    });
    return result;
  }
}
