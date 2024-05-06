package cn.xwlin.controller;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.xwlin.service.HelloService;
import cn.xwlin.timewheel.TimeWheel;

import java.util.List;

@RestController
@RequestMapping("hello")
public class HelloController {

  @Autowired
  private HelloService helloService;
  @Autowired
  private TimeWheel wheel;

  @RequestMapping("byName")
  public String hello(String name) {
    return helloService.hello(name);
  }


  @RequestMapping("addtask")
  public String addtask() throws InterruptedException {
    long l = System.currentTimeMillis();
    List<Long> next = Lists.newArrayList(l + 3000L, l + 10000, l + 30000, l + 100000);
    wheel.createTimerTask(100, next);
    wheel.createTimerTask(102, next);

    Thread.sleep(5000);
    wheel.removeTimerTask(100);

    long l2 = System.currentTimeMillis();
    List<Long> next2 = Lists.newArrayList(l2 + 5000L, l2 + 10000, l2 + 30000, l2 + 100000);
    wheel.createTimerTask(101, next2);

    return "SUCCESS";
  }
}
