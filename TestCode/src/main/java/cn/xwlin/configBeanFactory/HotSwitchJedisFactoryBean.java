package cn.xwlin.configBeanFactory;

public class HotSwitchJedisFactoryBean extends
    HotSwitchClientAbstractFactoryBean<HotSwitchJedisProxy, HotSwitchJedisConfig> {

  @Override
  public boolean isSingleton() {
    return true;
  }

}
