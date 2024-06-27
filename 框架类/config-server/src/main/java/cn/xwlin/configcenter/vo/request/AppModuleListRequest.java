package cn.xwlin.configcenter.vo.request;

/**
 * @author xiang.liao
 * @create 2024/6/27
 */
public class AppModuleListRequest {
  private int pageNum;
  private int pageSize;
  private String appCode;
  private String moduleCode;

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

  public String getAppCode() {
    return appCode;
  }

  public void setAppCode(String appCode) {
    this.appCode = appCode;
  }

  public String getModuleCode() {
    return moduleCode;
  }

  public void setModuleCode(String moduleCode) {
    this.moduleCode = moduleCode;
  }
}
