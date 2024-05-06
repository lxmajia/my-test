package cn.xwlin.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author MaJiaXueYuan.lx
 * @ClassName CglibProxy.java
 * @Description TODO
 * @createTime 2022年03月07日 22:30:00
 */
public class CglibProxy implements MethodInterceptor {

    private Object object;

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Cglib before");
        Object invoke = methodProxy.invokeSuper(o, objects);
        System.out.println("Cglib after");
        return invoke;
    }

    //定义获取代理对象方法
    public Object getCglibProxy(Object objectTarget) {
        //为目标对象target赋值
        this.object = objectTarget;
        Enhancer enhancer = new Enhancer();
        //设置父类,因为Cglib是针对指定的类生成一个子类，所以需要指定父类
        enhancer.setSuperclass(objectTarget.getClass());
        enhancer.setCallback(this);// 设置回调
        return enhancer.create();
    }

    public static void main(String[] args) {
        BizServer2 cglibProxy = (BizServer2) new CglibProxy().getCglibProxy(new BizServer2());
        cglibProxy.biz();
    }
}
