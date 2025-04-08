package cn.xwlin.config.factionbean.myredis;

import cn.xwlin.configcenter.refresh.IWlinConfigRefreshBeanFactory;
import cn.xwlin.server.EsServer;
import cn.xwlin.server.MyServerTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisClientConfig;
import redis.clients.jedis.commands.JedisCommands;

/**
 * @author xiang.liao
 * @create 2025/3/28
 */
public class MyRedisRefreshBean implements IWlinConfigRefreshBeanFactory<Jedis, MyRedisConfig> {


  public MyRedisRefreshBean() {
  }

  @Override
  public Jedis createBean(MyRedisConfig myRedisConfig) {
    Jedis redisConecct = new Jedis(myRedisConfig.getRedisUrl(), myRedisConfig.getRedisPort());
    redisConecct.auth(myRedisConfig.getUsername(), myRedisConfig.getPassword());
    redisConecct.set("name", "Test");
    return redisConecct;
  }

  @Override
  public boolean disposeOriBean(Jedis myRedisProxy) {
    if (myRedisProxy == null) {
      return true;
    }
    myRedisProxy.close();
    return true;
  }
}
