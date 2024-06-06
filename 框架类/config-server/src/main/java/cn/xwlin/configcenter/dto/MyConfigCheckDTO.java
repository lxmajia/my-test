package cn.xwlin.configcenter.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiang.liao
 * @create 2024/5/6
 */
public class MyConfigCheckDTO {
  private int newConfigChangeCount;
  private Long nextFetchTime;
  private Map<String, String> newConfigValueMap = new HashMap<>();

  public int getNewConfigChangeCount() {
    return newConfigChangeCount;
  }

  public void setNewConfigChangeCount(int newConfigChangeCount) {
    this.newConfigChangeCount = newConfigChangeCount;
  }

  public Long getNextFetchTime() {
    return nextFetchTime;
  }

  public void setNextFetchTime(Long nextFetchTime) {
    this.nextFetchTime = nextFetchTime;
  }

  public Map<String, String> getNewConfigValueMap() {
    return newConfigValueMap;
  }

  public void setNewConfigValueMap(Map<String, String> newConfigValueMap) {
    this.newConfigValueMap = newConfigValueMap;
  }
}
