package cn.xwlin.configBeanFactory;

import com.elong.hotswitch.client.Spring.IHotSwitchRefreshBeanFactory;
import redis.clients.jedis.JedisPool;

public class HotSwitchJedisRefresherFactoryBean implements
    IHotSwitchRefreshBeanFactory<HotSwitchJedisProxy, HotSwitchJedisConfig> {

  @Override
  public HotSwitchJedisProxy customCreateBean(HotSwitchJedisConfig newConfig,
      HotSwitchJedisProxy originObject, HotSwitchJedisConfig originConfig) {
    JedisPool jedisPool =
        new JedisPool(newConfig.getJedisPoolConfig(), newConfig.getHost(), newConfig.getPort(),
            newConfig.getTimeOut(), newConfig.getPassword());
    return new HotSwitchJedis(jedisPool);
  }

  @Override
  public boolean DisposeOriBean(HotSwitchJedisProxy originObject) {
    //销毁原连接池
    if (originObject != null && originObject.getJedisPool() != null) {
      originObject.getJedisPool().destroy();
    }
    return true;
  }
}
