package cn.xwlin.configcenter.vo.request;

/**
 * @author xiang.liao
 * @create 2024/6/26
 */
public class GetSysConfigReq {
  private int pageNum;
  private int pageSize;
  private Long appModuleId;

  private String configKey;

  public String getConfigKey() {
    return configKey;
  }

  public void setConfigKey(String configKey) {
    this.configKey = configKey;
  }

  public int getPageNum() {
    return pageNum;
  }

  public void setPageNum(int pageNum) {
    this.pageNum = pageNum;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public Long getAppModuleId() {
    return appModuleId;
  }

  public void setAppModuleId(Long appModuleId) {
    this.appModuleId = appModuleId;
  }
}
