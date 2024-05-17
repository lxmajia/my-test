package cn.xwlin.logic.nearby.req;

import lombok.ToString;

import java.math.BigDecimal;

@ToString
public class NearPlayerReq {
  /**
   * 经度
   */
  private BigDecimal longitude;
  /**
   * 纬度
   */
  private BigDecimal latitude;

  public BigDecimal getLongitude() {
    return longitude;
  }

  public void setLongitude(BigDecimal longitude) {
    this.longitude = longitude;
  }

  public BigDecimal getLatitude() {
    return latitude;
  }

  public void setLatitude(BigDecimal latitude) {
    this.latitude = latitude;
  }
}