package cn.xwlin.config.factionbean.myredis;

import cn.xwlin.configcenter.helper.CfgHelper;
import cn.xwlin.configcenter.refresh.WlinConfigAbstractFactoryBean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.commands.JedisCommands;

/**
 * @author xiang.liao
 * @create 2025/3/28
 */
@Component
public class MyRedisBeanFactory extends WlinConfigAbstractFactoryBean<Jedis, MyRedisConfig> {

  public MyRedisBeanFactory(CfgHelper cfgHelper) {
    super.setCfgHelper(cfgHelper);
    super.setRefreshBeanFactory(new MyRedisRefreshBean());
  }

  @Override
  public boolean isSingleton() {
    return true;
  }
}
