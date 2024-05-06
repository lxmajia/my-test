package cn.xwlin.serverb.rpc;

import cn.xwlin.serverb.rpc.req.HelloRequest;
import cn.xwlin.serverb.rpc.resp.HelloResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xiang.liao
 * @create 2023/11/10
 */
@FeignClient(value = "server-a")
public interface HelloFeign {
  @RequestMapping("/hello/say")
  HelloResponse sayhello(HelloRequest request);
}
