package cn.xwlin.util;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtils {
  public static Map<String, String> loadProperties(String classPathUrl) {
    ClassPathResource resource = new ClassPathResource(classPathUrl); // 指定属性文件路径
    Properties properties = new Properties();
    try (InputStream inputStream = resource.getInputStream()) {
      properties.load(inputStream);
    } catch (IOException e) {
      throw new RuntimeException("Failed to read properties file:" + classPathUrl, e);
    }

    return convertToMap(properties);
  }

  private static Map<String, String> convertToMap(Properties properties) {
    Map<String, String> map = new HashMap<>();
    for (Object key : properties.keySet()) {
      Object value = properties.getProperty((String) key);
      if (value != null) {
        map.put(String.valueOf(key), String.valueOf(value));
      }
    }
    return map;
  }
}