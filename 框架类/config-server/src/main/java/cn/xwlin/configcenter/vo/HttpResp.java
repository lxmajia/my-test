package cn.xwlin.configcenter.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HttpResp<T> {
  private int code;
  private String message;
  private T body;

  public HttpResp() {

  }

  public static HttpResp succuess() {
    HttpResp restResponse = new HttpResp();
    restResponse.setCode(0);
    return restResponse;
  }

  public static <T> HttpResp succuess(T body) {
    HttpResp restResponse = new HttpResp();
    restResponse.setCode(0);
    restResponse.setBody(body);

    return restResponse;
  }

  public static HttpResp fail(int failCode) {
    HttpResp restResponse = new HttpResp();
    restResponse.setCode(failCode);
    return restResponse;
  }


  public static HttpResp fail(int failCode, String message) {
    HttpResp restResponse = new HttpResp();
    restResponse.setCode(failCode);
    restResponse.setMessage(message);
    return restResponse;
  }

}