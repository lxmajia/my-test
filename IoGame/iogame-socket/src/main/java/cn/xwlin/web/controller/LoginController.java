package cn.xwlin.web.controller;

import cn.xwlin.web.service.UserService;
import cn.xwlin.web.vo.LoginInfo;
import cn.xwlin.web.vo.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/loginAjax")
    public RestResponse<LoginInfo> login(String accountName, String password) {
        LoginInfo login = userService.login(accountName, password);
        if (login == null) {
            return RestResponse.fail(401, "登录失败");
        }
        return RestResponse.succuess(login);
    }
}
