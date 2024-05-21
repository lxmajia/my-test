package cn.xwlin.configBeanFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author yao.wang2 2023/9/18
 */
public interface HotSwitchJedisProxy {

  JedisPool getJedisPool();

  Jedis getResource();
}
