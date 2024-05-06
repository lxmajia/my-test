package cn.xwlin.dubbo.client;

import cn.xwlin.dubbo.client.base.RpcReponse;
import cn.xwlin.dubbo.client.req.ByeByeRequest;
import cn.xwlin.dubbo.client.req.HelloRequest;
import cn.xwlin.dubbo.client.resp.ByeByeResponse;
import cn.xwlin.dubbo.client.resp.HelloResponse;

public interface ByeByeTestService {

    RpcReponse<ByeByeResponse> sayByeBye(ByeByeRequest request);
}
