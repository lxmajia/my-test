package cn.xwlin.configcenter.refresh;

import cn.xwlin.configcenter.excep.WlinConfigException;
import cn.xwlin.configcenter.helper.CfgHelper;
import cn.xwlin.configcenter.util.GenricUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.lang.reflect.Proxy;

import static org.springframework.util.Assert.notNull;


public abstract class WlinConfigAbstractFactoryBean<U, V> implements InitializingBean, FactoryBean<U> {
  private static Logger logger = LoggerFactory.getLogger(WlinConfigAbstractFactoryBean.class);

  private String key;
  private CfgHelper cfgHelper;
  private IWlinConfigRefreshBeanFactory<U, V> refreshBeanFactory;
  private boolean failedNotStart = true;

  public void setRefreshBeanFactory(IWlinConfigRefreshBeanFactory<U, V> refreshBeanFactory) {
    this.refreshBeanFactory = refreshBeanFactory;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public void setCfgHelper(CfgHelper cfgHelper) {
    this.cfgHelper = cfgHelper;
  }

  public void setFailedNotStart(boolean failedNotStart) {
    this.failedNotStart = failedNotStart;
  }

  @Override
  public final U getObject() throws WlinConfigException {
    long beginTime = System.currentTimeMillis();
    try {
      V configValue = cfgHelper.getConfig(key, (Class<V>) GenricUtil.getSuperClassGenricType(getClass(), 1));
      if (null != configValue) {
        U value = this.refreshBeanFactory.customCreateBean(configValue, null, null);
        return (U) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class<?>[]{getObjectType()}, new WlinConfigRefreshableProxy<>(this.refreshBeanFactory, this.key, this.cfgHelper, value, configValue, GenricUtil.getSuperClassGenricType(getClass(), 1)));
      } else {
        throw new WlinConfigException("GetConfigError, key:" + this.key);
      }
    } catch (Throwable throwable) {
      if (failedNotStart) {
        throw new WlinConfigException("FactoryBeanGetObjectError", throwable);
      } else {
        logger.error("");
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
    long beginTime = System.currentTimeMillis();
    try {
      checkInitConfig();
      init();
    } catch (Exception exx) {
      logger.error(exx.getMessage());
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
