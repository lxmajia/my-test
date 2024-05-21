package cn.xwlin.configBeanFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author yao.wang2 2023/9/18
 */
public class HotSwitchJedis implements HotSwitchJedisProxy {

  private JedisPool jedisPool;

  public HotSwitchJedis(JedisPool jedisPool) {
    this.jedisPool = jedisPool;
  }

  @Override
  public JedisPool getJedisPool() {
    return jedisPool;
  }

  @Override
  public Jedis getResource() {
    return jedisPool.getResource();
  }
}
