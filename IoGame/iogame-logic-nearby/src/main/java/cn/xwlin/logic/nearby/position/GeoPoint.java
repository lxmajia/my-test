package cn.xwlin.logic.nearby.position;

import java.math.BigDecimal;

/**
 * @author xiang.liao
 * @create 2024/5/27
 */
public class GeoPoint {
  /**
   * 纬度-x
   */
  private BigDecimal latitude;
  /**
   * 经度-y
   */
  private BigDecimal longitude;

  public BigDecimal getLatitude() {
    return latitude;
  }

  public void setLatitude(BigDecimal latitude) {
    this.latitude = latitude;
  }

  public BigDecimal getLongitude() {
    return longitude;
  }

  public void setLongitude(BigDecimal longitude) {
    this.longitude = longitude;
  }
}
