package cn.xwlin.configBeanFactory;

/**
 * Created by minji on 16/7/7.
 */
public interface IHotSwitchRefreshBeanFactory<U,V> {

  public abstract U customCreateBean(V newConfig, U originObject, V originConfig);

  public abstract boolean DisposeOriBean(U originObject);

}
