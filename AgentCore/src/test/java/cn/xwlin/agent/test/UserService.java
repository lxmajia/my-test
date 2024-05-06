package cn.xwlin.agent.test;

public class UserService {
    public void hello(String name){
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Hello " + name);
    }

    public void hello2(String name, Integer age) {
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Hello " + name + ",Age:" + age);
    }
}
