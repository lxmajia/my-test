package cn.xwlin.configcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ConfigCenterApp {
  public static void main(String[] args) {
    SpringApplication.run(ConfigCenterApp.class, args);
  }
}
