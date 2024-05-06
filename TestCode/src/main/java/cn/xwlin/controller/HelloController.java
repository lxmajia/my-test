package cn.xwlin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.xwlin.aop.Log;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * @author MaJiaXueYuan.lx
 * @ClassName aaa.java
 * @Description TODO
 * @createTime 2022年03月07日 21:17:00
 */
@RestController
public class HelloController {

    @RequestMapping("hello")
    @Log(name = "HelloController.hello")
    public String sayHello(String name) {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        request.startAsync();
        return "Hello " + name;
    }
}
