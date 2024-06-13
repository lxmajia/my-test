package cn.xwlin.config.biz;

import java.util.List;

/**
 * @author xiang.liao
 * @create 2024/6/13
 */
public class MyBizConfig {
  private String bizCode;
  private String bizName;
  private Boolean openFlag;
  private List<String> bizUrls;

  public String getBizCode() {
    return bizCode;
  }

  public void setBizCode(String bizCode) {
    this.bizCode = bizCode;
  }

  public String getBizName() {
    return bizName;
  }

  public void setBizName(String bizName) {
    this.bizName = bizName;
  }

  public Boolean getOpenFlag() {
    return openFlag;
  }

  public void setOpenFlag(Boolean openFlag) {
    this.openFlag = openFlag;
  }

  public List<String> getBizUrls() {
    return bizUrls;
  }

  public void setBizUrls(List<String> bizUrls) {
    this.bizUrls = bizUrls;
  }
}
