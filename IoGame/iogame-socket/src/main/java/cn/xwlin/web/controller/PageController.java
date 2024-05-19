package cn.xwlin.web.controller;

import cn.xwlin.web.service.UserService;
import cn.xwlin.web.vo.LoginInfo;
import cn.xwlin.web.vo.RestResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/auth")
public class PageController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String login() {
        return "index";
    }

    @RequestMapping("/near")
    public String nearby() {
        return "near";
    }


}
