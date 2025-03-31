package cn.xwlin.configcenter.refresh;

import cn.xwlin.configcenter.excep.WlinConfigException;
import cn.xwlin.configcenter.helper.CfgHelper;
import cn.xwlin.configcenter.util.GenricUtil;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.lang.reflect.Proxy;

import static org.springframework.util.Assert.notNull;


public abstract class WlinConfigAbstractFactoryBean<U, V> implements InitializingBean, FactoryBean<U> {
  private CfgHelper cfgHelper;
  private IWlinConfigRefreshBeanFactory<U, V> refreshBeanFactory;
  private boolean failedNotStart = true;

  public void setRefreshBeanFactory(IWlinConfigRefreshBeanFactory<U, V> refreshBeanFactory) {
    this.refreshBeanFactory = refreshBeanFactory;
  }

  public void setCfgHelper(CfgHelper cfgHelper) {
    this.cfgHelper = cfgHelper;
  }

  public void setFailedNotStart(boolean failedNotStart) {
    this.failedNotStart = failedNotStart;
  }

  @Override
  public final U getObject() throws WlinConfigException {
    try {
      V configValue = cfgHelper.getConfig((Class<V>) GenricUtil.getSuperClassGenricType(getClass(), 1));
      if (null != configValue) {
        U value = this.refreshBeanFactory.createBean(configValue);
        WlinConfigRefreshableCiglibProxy<U, V> ciglibProxy = new WlinConfigRefreshableCiglibProxy<>(this.refreshBeanFactory, configValue.getClass().getSimpleName(), this.cfgHelper, value, GenricUtil.getSuperClassGenricType(getClass(), 1));
        Object proxyInstance = ciglibProxy.getProxyInstance();
        return (U) proxyInstance;
      } else {
        throw new WlinConfigException("GetConfigError, key:" + configValue.getClass().getSimpleName());
      }
    } catch (Throwable throwable) {
      throwable.printStackTrace();
      if (failedNotStart) {
        throw new WlinConfigException("FactoryBeanGetObjectError", throwable);
      } else {
        return null;
      }
    }
  }

  @Override
  public Class<U> getObjectType() {
    return (Class<U>) GenricUtil.getSuperClassGenricType(getClass(), 0);
  }

  @Override
  public abstract boolean isSingleton();

  public final void afterPropertiesSet() throws WlinConfigException {
    try {
      checkInitConfig();
      init();
    } catch (Exception exx) {
      throw new WlinConfigException("Initialization of factoryBean failed", exx);
    }
  }

  protected void init() {
  }

  protected void checkInitConfig() {
    notNull(cfgHelper, "WlinConfHelper is null");
    notNull(refreshBeanFactory, "refreshBeanFactory is null");
  }
}
