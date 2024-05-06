package cn.xwlin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author MaJiaXueYuan.lx
 * @ClassName MjStartApp.java
 * @createTime 2022-5-12-0012
 */
@SpringBootApplication
public class TaskStartApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(TaskStartApp.class, args);
    }
}
