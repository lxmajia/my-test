package cn.xwlin.configcenter.vo;

import java.util.Map;

/**
 * @author xiang.liao
 * @create 2024/5/22
 */
public class GetConfigData {
  private String appCode;
  private String moduleCode;
  private long nextTimeMills;
  private int changeCount;
  private Map<String, String> changeConfigMap;

  public long getNextTimeMills() {
    return nextTimeMills;
  }

  public void setNextTimeMills(long nextTimeMills) {
    this.nextTimeMills = nextTimeMills;
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
