package cn.xwlin.servera.controller;

import cn.xwlin.servera.req.HelloRequest;
import cn.xwlin.servera.resp.HelloResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author xiang.liao
 * @create 2023/11/10
 */
@RequestMapping("hello")
@RestController
public class HelloController {

  @RequestMapping("say")
  public HelloResponse sayhello(@RequestBody HelloRequest request) {
    HelloResponse helloResponse = new HelloResponse();
    helloResponse.setHelloInfo("Hello " + request.getName() + ", age=" + request.getAge());
    helloResponse.setDate(new Date());
    return helloResponse;
  }
}
