package cn.xwlin.web.util;

import cn.xwlin.web.service.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SocketConnectUtil implements InitializingBean {

    private static UserService userService;

    public static UserService getUserService() {
        return userService;
    }


    @Autowired
    public void setUserService(UserService userService) {
        SocketConnectUtil.userService = userService;
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("AAA");
    }

}
