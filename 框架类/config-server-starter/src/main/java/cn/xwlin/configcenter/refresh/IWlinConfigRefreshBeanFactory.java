package cn.xwlin.configcenter.refresh;


public interface IWlinConfigRefreshBeanFactory<U, V> {

  U createBean(V newConfig);

  boolean disposeOriBean(U originObject);
}
