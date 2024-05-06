package cn.xwlin.designMode.代理模式.静态代理;

import cn.xwlin.designMode.代理模式.静态代理.impl.HelloServiceImpl ;

/**
 * 代理类实现了原接口，比较麻烦
 * 需要重写所有的代理方法
 */
public class JIngTaiProxy {
    public static void main(String[] args) {
        DoProxy doProxy = new DoProxy(new HelloServiceImpl());
        doProxy.hello("ZHANGSAN");
    }
}
