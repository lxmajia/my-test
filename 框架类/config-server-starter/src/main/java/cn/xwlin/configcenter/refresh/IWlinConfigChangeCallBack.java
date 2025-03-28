package cn.xwlin.configcenter.refresh;

import java.util.HashSet;


public interface IWlinConfigChangeCallBack {
  public void configModifyCallBack(String keySet);

  public void configItemAddCallBack(String ketSet);

  public void configItemDelCallBack(String ketSet);
}
