package cn.xwlin.rcgame.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xiang.liao
 * @create 2025/2/18
 */
public class DateUtil {
  public static Date parseDate(String date,String format) {
    try {
      Date d = new SimpleDateFormat(format).parse(date);
      return d;
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }
}
