package cn.xwlin.designMode.代理模式.动态代理;

public class HelloServiceImpl implements HelloService {
    @Override
    public void hello(String name) {
        System.out.println("Hello " + name);
    }
}
