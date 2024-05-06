package cn.xwlin.designMode.代理模式.静态代理.impl;

import cn.xwlin.designMode.代理模式.静态代理.HelloService ;

public class HelloServiceImpl implements HelloService {
    @Override
    public void hello(String name) {
        System.out.println("Hello " + name);
    }
}
