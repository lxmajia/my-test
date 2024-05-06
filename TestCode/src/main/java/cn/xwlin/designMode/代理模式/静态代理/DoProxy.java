package cn.xwlin.designMode.代理模式.静态代理;

public class DoProxy implements HelloService {

    HelloService helloService;

    public DoProxy(HelloService helloService) {
        this.helloService = helloService;
    }

    @Override
    public void hello(String name) {
        System.out.println("静态代理Start");
        helloService.hello("name");
        System.out.println("静态代理End");

    }
}
