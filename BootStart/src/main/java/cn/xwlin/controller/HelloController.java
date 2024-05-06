package cn.xwlin.controller;

import cn.xwlin.config.MyMappingProperties;
import cn.xwlin.dto.GetUserInfoRequest;
import cn.xwlin.logger.LoggerUtils;
import cn.xwlin.service.HelloService;
import cn.xwlin.vo.UserFullInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("hello")
public class HelloController {

  @Autowired
  private HelloService helloService;
  @Autowired
  private MyMappingProperties myMappingProperties;

  @RequestMapping("byName")
  public String hello(String name) {
    return helloService.hello(name);
  }

  @RequestMapping("getById")
  public UserFullInfo getById(@RequestBody @Valid GetUserInfoRequest request) {
    LoggerUtils.getControllerLogger().info("sssssssss");
    return helloService.getId(request.getId());
  }

  @RequestMapping("myMapping")
  public Map<String, String> myMapping() {
    System.out.println("AAA");
    return new HashMap<>();
  }

}
