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
  public HttpResp checkAppModule(String appCode,String moduleCode) {
    return configService.checkAppModule(appCode, moduleCode);
  }

  @RequestMapping("/refreshConfig")
  public DeferredResult<HttpResp<GetConfigData>> sayHello(String uniqueKey,
                                                          long oldVersion,
                                                          Long requestTimeout) {
    DeferredResult<HttpResp<GetConfigData>> result = new DeferredResult<>(requestTimeout - 5000);
    configChangeRequestHolder.addHolder(uniqueKey, oldVersion, result);
    result.onTimeout(() -> {
      result.setResult(HttpResp.fail(408,"timeout"));
    });
    return result;
  }
}
