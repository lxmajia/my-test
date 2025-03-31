package cn.xwlin.configcenter.refresh;

public interface IWlinConfigChangeCallBack {
  public void configModifyCallBack(String keySet);

  public void configItemAddCallBack(String ketSet);

  public void configItemDelCallBack(String ketSet);
}
