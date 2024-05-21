package cn.xwlin.configBeanFactory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.lang.reflect.Proxy;

import static org.springframework.util.Assert.notNull;

/**
 * Created by minji on 16/7/7.
 */
public abstract class HotSwitchClientAbstractFactoryBean<U, V> implements InitializingBean,FactoryBean<U> {


  // 读取配置的类
  private ConfigHelper hotSwitchConfigHelper;
  private String key;
  private IHotSwitchRefreshBeanFactory<U, V> IHotSwitchRefreshBeanFactory;


  public void setIHotSwitchRefreshBeanFactory(IHotSwitchRefreshBeanFactory<U, V> IHotSwitchRefreshBeanFactory) {
    this.IHotSwitchRefreshBeanFactory = IHotSwitchRefreshBeanFactory;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public void setHotSwitchConfigHelper(ConfigHelper hotSwitchConfigHelper) {
      this.hotSwitchConfigHelper = hotSwitchConfigHelper;
  }

  public final U getObject() {
    long beginTime=System.currentTimeMillis();
    try {
      V configValue = hotSwitchConfigHelper.GetConfigValue(key,
              (Class<V>) GenricUtil.getSuperClassGenricType(getClass(), 1));
      if (null != configValue) {
        U value=this.IHotSwitchRefreshBeanFactory.customCreateBean(configValue,null,null);
        return (U)Proxy.newProxyInstance(this.getClass().getClassLoader(),
            new Class<?>[] { getObjectType() },
            new HotSwitchRefreshableProxy<>(this.IHotSwitchRefreshBeanFactory, this.key,
                this.hotSwitchConfigHelper, value, configValue,GenricUtil.getSuperClassGenricType(getClass(), 1)));
      } else {
        throw new HotSwitchClientException("GetConfigError, key:" + this.key);
      }
    } catch (Throwable throwable) {
      throw new HotSwitchClientException("FactoryBeanGetObjectError", throwable);
    }
  }

  public final void afterPropertiesSet() throws HotSwitchClientException {
    long beginTime=System.currentTimeMillis();
    try {
      checkInitConfig();
//      if(!getObjectType().isInterface())
//      {
//        logger.error("U type should be a interface");
//      }
      init();
    }
    catch (Exception exx) {
      logger.error(ExceptionUtils.getFullStackTrace(exx));
      ExceptionLogHelper.SystemExceptionProcess(exx, beginTime, HotSwitchClientConst.ACTIONLOG_SERVICE_NAME, "PropertiesCheck_" + key);
      throw new HotSwitchClientException("Initialization of factoryBean failed", exx);
    }
  }

  protected void init(){}

  protected void checkInitConfig()
  {
    notNull(hotSwitchConfigHelper,"hotSwitchConfigHelper is null");
    notNull(IHotSwitchRefreshBeanFactory,"IHotSwitchRefreshBean is null");
  }


  public Class<U> getObjectType() {

    return (Class<U>) GenricUtil.getSuperClassGenricType(getClass(), 0);
  }

  public abstract boolean isSingleton();

}
