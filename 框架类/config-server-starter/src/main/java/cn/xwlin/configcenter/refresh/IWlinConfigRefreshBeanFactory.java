package cn.xwlin.configcenter.refresh;


public interface IWlinConfigRefreshBeanFactory<U, V> {

  public abstract U customCreateBean(V newConfig, U originObject, V originConfig);

  public abstract boolean DisposeOriBean(U originObject);

}
