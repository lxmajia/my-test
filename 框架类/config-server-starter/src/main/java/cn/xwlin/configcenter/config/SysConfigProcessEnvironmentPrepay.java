package cn.xwlin.configcenter.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author xiang.liao
 * @create 2024/6/7
 */
public class SysConfigProcessEnvironmentPrepay implements EnvironmentPostProcessor {
  @Override
  public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
    System.out.println(environment.getProperty("sys.name"));
  }
}
