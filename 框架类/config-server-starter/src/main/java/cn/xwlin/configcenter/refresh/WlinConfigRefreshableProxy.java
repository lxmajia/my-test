package cn.xwlin.configcenter.refresh;

import cn.xwlin.configcenter.helper.CfgHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by minji on 16/7/12.
 */
public class WlinConfigRefreshableProxy<U, V> implements InvocationHandler, IWlinConfigChangeCallBack {
  private static Logger logger = LoggerFactory.getLogger(WlinConfigRefreshableProxy.class);

  private IWlinConfigRefreshBeanFactory<U, V> iHWlinConfigRefreshBeanFactory;
  private CfgHelper cfgHelper;
  private String key;
  private U target;
  private V currentConfig;
  private Class<?> configClazz;

  public WlinConfigRefreshableProxy(IWlinConfigRefreshBeanFactory iHWlinConfigRefreshBeanFactory, String key, CfgHelper cfgHelper, U target, V config, Class configClazz) {
    this.iHWlinConfigRefreshBeanFactory = iHWlinConfigRefreshBeanFactory;
    this.cfgHelper = cfgHelper;
    this.cfgHelper.registerCustomCallback(key, this);
    this.key = key;
    this.target = target;
    this.currentConfig = config;
    this.configClazz = configClazz;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    return method.invoke(target, args);
  }

  @Override
  public void configModifyCallBack(String refreshKey) {
    if (StringUtils.hasLength(refreshKey)) {
      if (refreshKey.equals(key)) {
        try {
          V newConfig = cfgHelper.getConfig(key, (Class<V>) configClazz);
          logger.info("WlinConfigRefreshableProxy-Callback获取变更配置成功,key:" + key);
          U newTarget = this.iHWlinConfigRefreshBeanFactory.customCreateBean(newConfig, this.target, this.currentConfig);
          U originTarget = this.target;
          this.target = newTarget;
          logger.info("WlinConfigRefreshableProxy-Callback替换bean对象成功,key:" + key);
          this.currentConfig = newConfig;
          this.iHWlinConfigRefreshBeanFactory.DisposeOriBean(originTarget);
          logger.info("WlinConfigRefreshableProxy-Callback销毁原始bean对象成功,key:" + key);
        } catch (Throwable throwable) {
          logger.error(throwable.getMessage());
        }
      }
    }
  }

  @Override
  public void configItemAddCallBack(String ketSet) {

  }

  @Override
  public void configItemDelCallBack(String ketSet) {

  }
}
