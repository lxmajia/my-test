package cn.xwlin.web.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class WebServerUtil implements InitializingBean {

  private static StringRedisTemplate stringRedisTemplate;

  private static RedisTemplate<Object, Object> redisTemplate;

  @Override
  public void afterPropertiesSet() {
    System.out.println("AAA");
  }

  public static StringRedisTemplate getStringRedisTemplate() {
    return stringRedisTemplate;
  }

  public static RedisTemplate<Object, Object> getRedisTemplate() {
    return redisTemplate;
  }

  @Autowired
  public void setRedisTemplate(RedisTemplate redisTemplate) {
    WebServerUtil.redisTemplate = redisTemplate;
  }

  @Autowired
  public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
    WebServerUtil.stringRedisTemplate = stringRedisTemplate;
  }

}
