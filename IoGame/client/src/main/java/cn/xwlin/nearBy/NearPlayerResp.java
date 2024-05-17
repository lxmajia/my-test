package cn.xwlin.nearBy;

import lombok.ToString;

import java.util.List;

@ToString
public class NearPlayerResp {
  private List<NearPlayerInfo> nearPlayerInfoList;

  public List<NearPlayerInfo> getNearPlayerInfoList() {
    return nearPlayerInfoList;
  }

  public void setNearPlayerInfoList(List<NearPlayerInfo> nearPlayerInfoList) {
    this.nearPlayerInfoList = nearPlayerInfoList;
  }
}