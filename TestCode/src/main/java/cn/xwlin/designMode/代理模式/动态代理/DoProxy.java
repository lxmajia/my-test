package cn.xwlin.designMode.代理模式.动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DoProxy implements InvocationHandler {

    Object proxyObj;

    public Object createProxy() {
        return Proxy.newProxyInstance(proxyObj.getClass().getClassLoader(), proxyObj.getClass().getInterfaces(), this);
    }

    public DoProxy(Object realService) {
        this.proxyObj = realService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("JDK动态代理START");
        Object invoke = method.invoke(proxyObj, args);
        System.out.println("JDK动态代理END");
        return invoke;
    }
}
