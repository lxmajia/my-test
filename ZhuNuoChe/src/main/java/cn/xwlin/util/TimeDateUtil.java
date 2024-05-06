package cn.xwlin.util;

import java.text.SimpleDateFormat;

public final class TimeDateUtil {

    private TimeDateUtil() {
    }

    /**
     * 默认日期格式
     */
    private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 把当前时间格式化成yyyy-MM-dd HH:mm:ss
     *
     * @return String
     */
    public static String date() {
        return new SimpleDateFormat(DEFAULT_FORMAT).format(System.currentTimeMillis());
    }
}
