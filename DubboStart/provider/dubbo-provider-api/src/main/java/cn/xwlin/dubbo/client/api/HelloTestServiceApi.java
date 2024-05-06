package cn.xwlin.dubbo.client.api;

import cn.xwlin.dubbo.client.HelloTestService;
import cn.xwlin.dubbo.client.base.RpcReponse;
import cn.xwlin.dubbo.client.dao.UserInfoDao;
import cn.xwlin.dubbo.client.req.HelloRequest;
import cn.xwlin.dubbo.client.resp.HelloResponse;
import com.alibaba.dubbo.config.annotation.Service;

import java.util.Date;

@Service(timeout = 6000)
public class HelloTestServiceApi implements HelloTestService {

    private final UserInfoDao userInfoDao;

    public HelloTestServiceApi(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }


    @Override
    public RpcReponse<HelloResponse> sayHello(HelloRequest request) {
        if (request.getName() == null) {
            return RpcReponse.failed(100, "Name不能为空");
        }
        userInfoDao.getName();

        HelloResponse response = new HelloResponse();
        response.setHelloInfo("Hello Name : " + request.getName());
        response.setDate(new Date());
        return RpcReponse.success(response);
    }
}
