package cn.xwlin.logic.nearby.position;

/**
 * @author xiang.liao
 * @create 2024/5/27
 */
public class GamePoint {
  /**
   * 屏幕横向（对应纬度latitude）
   */
  private Long x;
  /**
   * 屏幕竖向（对应经度longitude）
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
