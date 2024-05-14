package cn.xwlin.web.controller;

import cn.xwlin.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(String accountName, String password) {
        return userService.login(accountName, password);
    }
}
