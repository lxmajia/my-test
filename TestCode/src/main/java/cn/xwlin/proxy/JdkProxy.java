package cn.xwlin.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author MaJiaXueYuan.lx
 * @ClassName JdkProxy.java
 * @Description TODO
 * @createTime 2022年03月07日 22:23:00
 */
public class JdkProxy implements InvocationHandler {

    private Object object;

    public JdkProxy(Object bizService) {
        this.object = bizService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("JDK before");
        Object invoke = method.invoke(object, args);
        System.out.println("JDK after");
        return invoke;
    }

    public static void main(String[] args) {
        BizServiceImpl bizService = new BizServiceImpl();

        JdkProxy jdkProxy = new JdkProxy(bizService);


        BizService bizService1 = (BizService) Proxy.newProxyInstance(jdkProxy.getClass().getClassLoader(), bizService.getClass().getInterfaces(), jdkProxy);
        bizService1.biz();
    }
}
