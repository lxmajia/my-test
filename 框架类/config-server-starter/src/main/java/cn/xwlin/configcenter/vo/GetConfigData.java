package cn.xwlin.configcenter.vo;

import java.util.Map;

/**
 * @author xiang.liao
 * @create 2024/5/22
 */
public class GetConfigData {
  private long appCode;
  private long moduleCode;
  private int changeCount;
  private Map<String, String> changeConfigMap;

  public long getAppCode() {
    return appCode;
  }

  public void setAppCode(long appCode) {
    this.appCode = appCode;
  }

  public long getModuleCode() {
    return moduleCode;
  }

  public void setModuleCode(long moduleCode) {
    this.moduleCode = moduleCode;
  }

  public int getChangeCount() {
    return changeCount;
  }

  public void setChangeCount(int changeCount) {
    this.changeCount = changeCount;
  }

  public Map<String, String> getChangeConfigMap() {
    return changeConfigMap;
  }

  public void setChangeConfigMap(Map<String, String> changeConfigMap) {
    this.changeConfigMap = changeConfigMap;
  }
}
