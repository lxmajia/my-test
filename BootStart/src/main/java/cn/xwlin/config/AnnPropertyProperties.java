package cn.xwlin.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author xiang.liao
 * @create 2024/2/28
 */
@Data
@Component
@ConfigurationProperties(prefix = "ann")
@PropertySource(value = "classpath:config/ann_property.properties", encoding = "utf-8")
public class AnnPropertyProperties {
  private String name;
  private String sex;
  private String phone;
}
