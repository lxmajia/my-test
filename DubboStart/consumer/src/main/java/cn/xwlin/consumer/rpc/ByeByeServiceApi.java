package cn.xwlin.consumer.rpc;

import cn.xwlin.dubbo.client.ByeByeTestService;
import cn.xwlin.dubbo.client.HelloTestService;
import cn.xwlin.dubbo.client.base.RpcReponse;
import cn.xwlin.dubbo.client.req.ByeByeRequest;
import cn.xwlin.dubbo.client.req.HelloRequest;
import cn.xwlin.dubbo.client.resp.ByeByeResponse;
import cn.xwlin.dubbo.client.resp.HelloResponse;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

@Component
public class ByeByeServiceApi {


    @Reference
    private ByeByeTestService byeByeTestService;

    public RpcReponse<ByeByeResponse> byebye(String name) {
        ByeByeRequest req = new ByeByeRequest();
        req.setName(name);
        req.setAge(100);
        return byeByeTestService.sayByeBye(req);
    }

}
