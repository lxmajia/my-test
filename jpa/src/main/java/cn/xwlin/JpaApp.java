package cn.xwlin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author xiang.liao
 * @create 2023/8/31
 */
@SpringBootApplication
@EntityScan("cn.xwlin.entity")
@EnableJpaRepositories(basePackages = "cn.xwlin.mapper")
public class JpaApp {
  public static void main(String[] args) {
    SpringApplication.run(JpaApp.class, args);
  }
}
