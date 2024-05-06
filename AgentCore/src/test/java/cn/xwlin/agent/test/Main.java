package cn.xwlin.agent.test;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        userService.hello("QWER");
        userService.hello2("QWER", 15);
    }
}