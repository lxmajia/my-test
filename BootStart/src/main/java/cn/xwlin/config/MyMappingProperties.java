package cn.xwlin.config;

import cn.xwlin.util.PropertiesUtils;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiang.liao
 * @create 2024/2/28
 */
@Data
@Configuration
public class MyMappingProperties implements InitializingBean {
  private Map<String, String> myMapping = new HashMap<>();

  @Override
  public void afterPropertiesSet() {
    Map<String, String> stringStringMap = PropertiesUtils.loadProperties("myMapping.properties");
    myMapping = stringStringMap;
  }
}
