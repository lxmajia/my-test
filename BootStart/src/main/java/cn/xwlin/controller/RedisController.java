package cn.xwlin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("redis")
public class RedisController {

  @Autowired
  private StringRedisTemplate redisTemplate;

  @RequestMapping("set")
  public String info(String key, String value) {
    redisTemplate.opsForValue().set(key, value);
    return "OK";
  }

  @RequestMapping("get")
  public String get(String key) {
    return redisTemplate.opsForValue().get(key);
  }

}
