package cn.xwlin.logic.nearby.req;

import lombok.ToString;

import java.math.BigDecimal;

@ToString
public class NearPlayerReq {
  /**
   * x纬度
   */
  private Long x;
  /**
   * y经度
   */
  private Long y;

  public Long getX() {
    return x;
  }

  public void setX(Long x) {
    this.x = x;
  }

  public Long getY() {
    return y;
  }

  public void setY(Long y) {
    this.y = y;
  }
}