package cn.xwlin.logic.nearby.resp;

import cn.xwlin.logic.nearby.resp.data.NearPlayerInfo;
import lombok.ToString;

import java.math.BigDecimal;
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