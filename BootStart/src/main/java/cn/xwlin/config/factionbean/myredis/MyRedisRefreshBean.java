package cn.xwlin.config.factionbean.myredis;

import cn.xwlin.configcenter.refresh.IWlinConfigRefreshBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author xiang.liao
 * @create 2025/3/28
 */
public class MyRedisRefreshBean implements IWlinConfigRefreshBeanFactory<MyRedisProxy, MyRedisConfig> {
  @Override
  public MyRedisProxy customCreateBean(MyRedisConfig myRedisConfig, MyRedisProxy myRedisProxy, MyRedisConfig v1) {
    MyRedisProxy myRedisProxy1 = new MyRedisProxy();
    myRedisProxy1.setRedisUrl(myRedisConfig.getRedisUrl());
    myRedisProxy1.setRedisPort(myRedisConfig.getRedisPort());
    myRedisProxy1.setLinkUrl(myRedisProxy.getLinkUrl() + ":" + myRedisConfig.getRedisPort());
    return myRedisProxy1;
  }

  @Override
  public boolean DisposeOriBean(MyRedisProxy myRedisProxy) {
    if (myRedisProxy == null) {
      return true;
    }
    myRedisProxy = null;
    return true;
  }
}
