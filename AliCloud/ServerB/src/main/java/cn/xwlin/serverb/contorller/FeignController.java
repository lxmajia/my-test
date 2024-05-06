package cn.xwlin.serverb.contorller;

import cn.xwlin.serverb.rpc.HelloFeign;
import cn.xwlin.serverb.rpc.req.HelloRequest;
import cn.xwlin.serverb.rpc.resp.HelloResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiang.liao
 * @create 2023/11/10
 */
@RestController
@RequestMapping("feign")
public class FeignController {

  @Autowired
  private HelloFeign helloFeign;

  @RequestMapping("sayHello")
  public HelloResponse sayHello() {
    HelloRequest request = new HelloRequest();
    request.setName(System.currentTimeMillis() + "");
    request.setAge(123);
    return helloFeign.sayhello(request);
  }

}
