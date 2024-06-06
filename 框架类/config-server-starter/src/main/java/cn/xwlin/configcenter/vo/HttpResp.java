package cn.xwlin.configcenter.vo;

import cn.xwlin.configcenter.util.OSUtils;


public class HttpResp<T> {
  private int code;
  private String message;
  private String serverIp;
  private T body;

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getServerIp() {
    return serverIp;
  }

  public void setServerIp(String serverIp) {
    this.serverIp = serverIp;
  }

  public T getBody() {
    return body;
  }

  public void setBody(T body) {
    this.body = body;
  }

  public HttpResp() {
    this.serverIp = OSUtils.getLocalIP();
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