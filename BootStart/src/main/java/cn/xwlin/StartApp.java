package cn.xwlin;

import cn.xwlin.spring.MyImportSelector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

/**
 * @author MaJiaXueYuan.lx
 * @ClassName MjStartApp.java
 * @createTime 2022-5-12-0012
 */
@Import(MyImportSelector.class)
@SpringBootApplication
public class StartApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(StartApp.class, args);
        DataSource bean = run.getBean(DataSource.class);
        System.out.println(bean.getClass().toString());
    }
}
