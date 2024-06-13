package cn.xwlin.controller;

import cn.xwlin.config.AnnPropertyProperties;
import cn.xwlin.config.PropMapUtilLoadProperties;
import cn.xwlin.config.biz.MyBizConfig;
import cn.xwlin.configcenter.helper.CfgHelper;
import cn.xwlin.dto.GetUserInfoRequest;
import cn.xwlin.logger.LoggerUtils;
import cn.xwlin.service.HelloService;
import cn.xwlin.vo.UserFullInfo;
import com.alibaba.fastjson2.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("hello")
public class HelloController {

  @Autowired
  private HelloService helloService;
  @Autowired
  private PropMapUtilLoadProperties myMappingProperties;
  @Autowired
  private AnnPropertyProperties annPropertyProperties;
  @Autowired
  private CfgHelper cfgHelper;
  @Value("${sys.name}")
  private String sysName;

  @RequestMapping("myMapping")
  public String myMapping() {
    return JSON.toJSONString(myMappingProperties.getMyMapping());
  }

  @RequestMapping("myBizConfig")
  public String myBizConfig() {
    MyBizConfig config = cfgHelper.getConfig(MyBizConfig.class);
    return JSON.toJSONString(config);
  }

  @RequestMapping("annMapping")
  public String annMapping() {
    return JSON.toJSONString(annPropertyProperties);
  }


  @RequestMapping("byName")
  public String hello(String name) {
    return helloService.hello(name);
  }

  @RequestMapping("sysName")
  public String sysName() {
    return sysName;
  }

  @RequestMapping("getById")
  public UserFullInfo getById(@RequestBody @Valid GetUserInfoRequest request) {
    LoggerUtils.getControllerLogger().info("sssssssss");
    return helloService.getId(request.getId());
  }
}
