package cn.xwlin.configcenter.refresh;

import cn.xwlin.configcenter.helper.CfgHelper;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.logging.Logger;


public class WlinConfigRefreshableCiglibProxy<U, V> implements MethodInterceptor, IWlinConfigChangeCallBack {
  private static Logger logger = Logger.getLogger("WlinConfig");

  private IWlinConfigRefreshBeanFactory<U, V> iHWlinConfigRefreshBeanFactory;
  private CfgHelper cfgHelper;
  private String key;
  private U target;
  private Class<?> configClazz;

  public WlinConfigRefreshableCiglibProxy(IWlinConfigRefreshBeanFactory iHWlinConfigRefreshBeanFactory, String key, CfgHelper cfgHelper, U target, Class configClazz) {
    this.iHWlinConfigRefreshBeanFactory = iHWlinConfigRefreshBeanFactory;
    this.cfgHelper = cfgHelper;
    this.cfgHelper.registerCustomCallback(key, this);
    this.key = key;
    this.target = target;
    this.configClazz = configClazz;
  }

  @Override
  public void configModifyCallBack(String refreshKey) {
    if (StringUtils.hasLength(refreshKey)) {
      if (refreshKey.equals(key)) {
        try {
          V newConfig = cfgHelper.getConfig(key, (Class<V>) configClazz);
          logger.info("WlinConfigRefreshableProxy-Callback获取变更配置成功,key:" + key);
          U newTarget = this.iHWlinConfigRefreshBeanFactory.createBean(newConfig);
          U originTarget = this.target;
          this.target = newTarget;
          logger.info("WlinConfigRefreshableProxy-Callback替换bean对象成功,key:" + key);
          this.iHWlinConfigRefreshBeanFactory.disposeOriBean(originTarget);
          logger.info("WlinConfigRefreshableProxy-Callback销毁原始bean对象成功,key:" + key);
        } catch (Throwable throwable) {
          logger.warning(throwable.getMessage());
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

  public Object getProxyInstance() {
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(target.getClass());
    enhancer.setCallback(this);
    return enhancer.create();
  }

  @Override
  public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
    return method.invoke(target, objects);
  }
}
