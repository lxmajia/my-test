package cn.xwlin.httphold;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.HttpUtil;

import java.util.Map;

public class HttpUtils {
    public static String get(String url){
        return HttpUtil.get("url", CharsetUtil.CHARSET_UTF_8);
    }

    public static String post(String url, Map<String, Object> paramMap){
        return HttpUtil.get("url", CharsetUtil.CHARSET_UTF_8);
    }
}
