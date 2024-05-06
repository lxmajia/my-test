package cn.xwlin.consumer.rpc;

import cn.xwlin.dubbo.client.HelloTestService;
import cn.xwlin.dubbo.client.base.RpcReponse;
import cn.xwlin.dubbo.client.req.HelloRequest;
import cn.xwlin.dubbo.client.resp.HelloResponse;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloServiceApi {

    @Reference
    private HelloTestService helloTestService;

    public RpcReponse<HelloResponse> hello(String name) {
        HelloRequest req = new HelloRequest();
        req.setName(name);
        req.setAge(100);
        log.info("调用helloTestService,req:{}", JSON.toJSONString(req));
        return helloTestService.sayHello(req);
    }

}
