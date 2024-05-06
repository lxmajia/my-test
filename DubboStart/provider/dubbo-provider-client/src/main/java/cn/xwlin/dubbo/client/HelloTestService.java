package cn.xwlin.dubbo.client;

import cn.xwlin.dubbo.client.base.RpcReponse;
import cn.xwlin.dubbo.client.req.HelloRequest;
import cn.xwlin.dubbo.client.resp.HelloResponse;

public interface HelloTestService {

    RpcReponse<HelloResponse> sayHello(HelloRequest request);
}
