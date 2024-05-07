package cn.xwlin.controller;

import cn.xwlin.holder.ConfigChangeRequestHolder;
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
public class DeferredResultController {

  @Autowired
  private ConfigChangeRequestHolder configChangeRequestHolder;

  @RequestMapping("/getNewVersion")
  public DeferredResult<String> sayHello(String configKey, String oldVersion,
                                         Long requestTimeout) {
    DeferredResult<String> result = new DeferredResult<>(requestTimeout - 5000);
    String key = configKey + "###" + oldVersion;
    configChangeRequestHolder.addCheck(key, result);
    return result;
  }

  @SneakyThrows
  @RequestMapping("/testDeferredResult")
  public DeferredResult<String> test() {
    DeferredResult<String> result = new DeferredResult<>(5000L);
    Thread.sleep(2000L);
    result.setResult("123456");
    return result;
  }

  @RequestMapping("/hello")
  public String hello() {
    return "hello world;";
  }
}
