package cn.xwlin.servera;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author xiang.liao
 * @create 2023/11/10
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ServerAApp {
  public static void main(String[] args) {
    SpringApplication.run(ServerAApp.class, args);
  }
}
