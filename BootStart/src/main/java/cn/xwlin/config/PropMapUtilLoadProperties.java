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
public class PropMapUtilLoadProperties implements InitializingBean {
  private Map<String, String> myMapping = new HashMap<>();

  @Override
  public void afterPropertiesSet() {
    Map<String, String> stringStringMap = PropertiesUtils.loadProperties("config/prop_map_util_load.properties");
    myMapping = stringStringMap;
  }
}
