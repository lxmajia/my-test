package cn.xwlin.designMode.代理模式.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

class DoProxyx implements MethodInterceptor {


    public Object createProxy(Class<?> clazz) {
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(this);
        enhancer.setSuperclass(clazz);
        return enhancer.create();
    }


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("CGLIB动态代理开始");
        Object invoke = methodProxy.invokeSuper(o, objects);
        System.out.println("Cilib动态代理结束");
        return invoke;
    }
}
