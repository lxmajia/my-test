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
  private List<String> bizUrls2;
  private List<String> bizUrls3;
  private List<String> bizUrls4;
  private List<String> bizUrls5;


  public List<String> getBizUrls2() {
    return bizUrls2;
  }

  public void setBizUrls2(List<String> bizUrls2) {
    this.bizUrls2 = bizUrls2;
  }

  public List<String> getBizUrls3() {
    return bizUrls3;
  }

  public void setBizUrls3(List<String> bizUrls3) {
    this.bizUrls3 = bizUrls3;
  }

  public List<String> getBizUrls4() {
    return bizUrls4;
  }

  public void setBizUrls4(List<String> bizUrls4) {
    this.bizUrls4 = bizUrls4;
  }

  public List<String> getBizUrls5() {
    return bizUrls5;
  }

  public void setBizUrls5(List<String> bizUrls5) {
    this.bizUrls5 = bizUrls5;
  }

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
