package cn.xwlin.consumer.controller;

import cn.xwlin.consumer.dao.UserInfoDao;
import cn.xwlin.consumer.rpc.ByeByeServiceApi;
import cn.xwlin.consumer.rpc.HelloServiceApi;
import cn.xwlin.dubbo.client.base.RpcReponse;
import cn.xwlin.dubbo.client.resp.ByeByeResponse;
import cn.xwlin.dubbo.client.resp.HelloResponse;
import com.alibaba.druid.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dubbo")
public class DubboTestController {

    private final HelloServiceApi helloServiceApi;
    private final ByeByeServiceApi byeByeServiceApi;
    private final UserInfoDao userInfoDao;


    public DubboTestController(HelloServiceApi helloServiceApi, ByeByeServiceApi byeByeServiceApi, UserInfoDao userInfoDao) {
        this.helloServiceApi = helloServiceApi;
        this.byeByeServiceApi = byeByeServiceApi;
        this.userInfoDao = userInfoDao;
    }

    @RequestMapping("hello")
    public RpcReponse<HelloResponse> sayHello() {
        String name = userInfoDao.getName();
        if (StringUtils.isEmpty(name)) {
            name = "NotFindHello";
        }
        return helloServiceApi.hello(name);
    }

    @RequestMapping("byebye")
    public RpcReponse<ByeByeResponse> sayByeBye() {
        String name = userInfoDao.getName();
        if (StringUtils.isEmpty(name)) {
            name = "NotFindHello";
        }
        return byeByeServiceApi.byebye(name);
    }
}
