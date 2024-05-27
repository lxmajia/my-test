package cn.xwlin.logic.nearby.position;
import java.math.BigDecimal;

/**
 * @author xiang.liao
 * @create 2024/5/27
 */
public class GameGeoUtils {

  private static final int SCALE = 6;
  private static final BigDecimal TRANS_RATE = BigDecimal.valueOf(1000000);

  /**
   * 利用坐标系的六位小数单位(四舍五入)  25,5
   */
  public static GeoPoint transToGeoPoint(Long x, Long y) {
    GeoPoint geoPoint = new GeoPoint();
    BigDecimal latitude = BigDecimal.valueOf(x).divide(TRANS_RATE,SCALE,BigDecimal.ROUND_HALF_DOWN);
    BigDecimal longitude = BigDecimal.valueOf(y).divide(TRANS_RATE,SCALE,BigDecimal.ROUND_HALF_DOWN);
    geoPoint.setLatitude(latitude);
    geoPoint.setLongitude(longitude);
    return geoPoint;
  }

  public static GamePoint transToGamePoint(BigDecimal latitude, BigDecimal longitude) {
    GamePoint gamePoint = new GamePoint();
    long x = latitude.multiply(TRANS_RATE).longValue();
    long y = longitude.multiply(TRANS_RATE).longValue();
    gamePoint.setX(x);
    gamePoint.setY(y);
    return gamePoint;
  }
}
