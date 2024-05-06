package cn.xwlin.designMode.代理模式.动态代理;

/**
 * JDK动态代理，需要接口
 */
public class JdkDongTaiProxy {
    public static void main(String[] args) {
        DoProxy doProxy = new DoProxy(new HelloServiceImpl());
        HelloService proxy = (HelloService) doProxy.createProxy();
        proxy.hello("DongTai1!!!");
    }
}
