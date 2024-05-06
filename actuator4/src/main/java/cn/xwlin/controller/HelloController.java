package cn.xwlin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class HelloController {
    @RequestMapping("byName")
    public String hello(String name) {
        return "Hello" + name;
    }
}
