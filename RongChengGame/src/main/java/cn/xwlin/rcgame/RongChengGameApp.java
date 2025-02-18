package cn.xwlin.rcgame;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author xiang.liao
 * @create 2023/9/1
 */
@SpringBootApplication
@MapperScan(basePackages = {"cn.xwlin.rcgame.dao"})
public class RongChengGameApp {
  public static void main(String[] args) {
    SpringApplication.run(RongChengGameApp.class, args);
  }
}
