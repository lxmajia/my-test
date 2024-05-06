package cn.xwlin.designMode.代理模式.cglib;

/**
 * Cglib动态代理不需要接口
 * 内部实现子类，继承，无法代理final的类和方法
 * 字节码技术
 */
public class CglibMain {
    public static void main(String[] args) {
        DoProxyx doProxyx = new DoProxyx();
        HelloServer proxy = (HelloServer) doProxyx.createProxy(HelloServer.class);
        proxy.hello("CgLib!!!!");
    }
}
