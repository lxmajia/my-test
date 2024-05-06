package cn.xwlin.controller;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("redis")
public class RedissonController {

  @Autowired
  private RedissonClient redissonClient;
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

  @RequestMapping("lockTest")
  public String lockTest(String lockKey) throws Exception {
    RLock lock = redissonClient.getLock(lockKey);
    boolean b = lock.tryLock();
    if (b) {
      log.info("#LockSuccess#lockKey:" + lockKey);
    }
    try {
      Thread.sleep(5000);
    } catch (Throwable t) {
    } finally {
      lock.unlock();
    }
    return "LockSuccess";
  }
}
