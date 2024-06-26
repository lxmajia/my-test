package cn.xwlin.configcenter.vo.resp;

import cn.xwlin.configcenter.util.OSUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HttpResp<T> {
  private int code;
  private String message;
  private String serverIp;
  private Long serverTime;
  private T body;

  public HttpResp() {
    this.serverIp = OSUtils.getLocalIP();
  }

  public static  <T> HttpResp <T> success() {
    HttpResp <T> restResponse = new HttpResp<T>();
    restResponse.setCode(0);
    return restResponse;
  }

  public static <T> HttpResp<T> success(T body) {
    HttpResp<T> restResponse = new HttpResp<T>();
    restResponse.setCode(0);
    restResponse.setBody(body);

    return restResponse;
  }

  public static  <T> HttpResp <T> fail(int failCode) {
    HttpResp <T> restResponse = new HttpResp <T>();
    restResponse.setCode(failCode);
    return restResponse;
  }


  public static <T> HttpResp <T> fail(int failCode, String message) {
    HttpResp<T> restResponse = new HttpResp <T>();
    restResponse.setCode(failCode);
    restResponse.setMessage(message);
    return restResponse;
  }

}