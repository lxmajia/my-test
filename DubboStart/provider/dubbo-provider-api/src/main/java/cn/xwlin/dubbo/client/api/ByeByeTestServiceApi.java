package cn.xwlin.dubbo.client.api;

import cn.xwlin.dubbo.client.ByeByeTestService;
import cn.xwlin.dubbo.client.base.RpcReponse;
import cn.xwlin.dubbo.client.dao.UserInfoDao;
import cn.xwlin.dubbo.client.req.ByeByeRequest;
import cn.xwlin.dubbo.client.resp.ByeByeResponse;
import com.alibaba.dubbo.config.annotation.Service;

import java.util.Date;

@Service
public class ByeByeTestServiceApi implements ByeByeTestService {

    private final UserInfoDao userInfoDao;

    public ByeByeTestServiceApi(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }

    @Override
    public RpcReponse<ByeByeResponse> sayByeBye(ByeByeRequest request) {
        if (request.getName() == null) {
            return RpcReponse.failed(100, "Name不能为空");
        }
        userInfoDao.getName();

        ByeByeResponse response = new ByeByeResponse();
        response.setHelloInfo("ByeBye Name : " + request.getName());
        response.setDate(new Date());
        return RpcReponse.success(response);
    }
}
