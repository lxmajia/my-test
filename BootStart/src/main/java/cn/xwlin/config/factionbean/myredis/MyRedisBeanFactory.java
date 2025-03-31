package cn.xwlin.config.factionbean.myredis;

import cn.xwlin.configcenter.helper.CfgHelper;
import cn.xwlin.configcenter.refresh.WlinConfigAbstractFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xiang.liao
 * @create 2025/3/28
 */
@Component
public class MyRedisBeanFactory extends WlinConfigAbstractFactoryBean<MyRedisProxy, MyRedisConfig> {

  public MyRedisBeanFactory(CfgHelper cfgHelper) {
    setCfgHelper(cfgHelper);
    setRefreshBeanFactory(new MyRedisRefreshBean());
    setKey("MyRedisConfig");
  }

  @Override
  public boolean isSingleton() {
    return true;
  }
}
