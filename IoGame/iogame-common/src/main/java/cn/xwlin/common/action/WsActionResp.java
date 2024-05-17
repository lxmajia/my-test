package cn.xwlin.common.action;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsActionResp<T> {
  private int code;
  private String message;
  private T body;

  public WsActionResp() {

  }

  public static WsActionResp succuess() {
    WsActionResp restResponse = new WsActionResp();
    restResponse.setCode(0);
    return restResponse;
  }

  public static <T> WsActionResp succuess(T body) {
    WsActionResp restResponse = new WsActionResp();
    restResponse.setCode(0);
    restResponse.setBody(body);

    return restResponse;
  }

  public static WsActionResp fail(int failCode) {
    WsActionResp restResponse = new WsActionResp();
    restResponse.setCode(failCode);
    return restResponse;
  }


  public static WsActionResp fail(int failCode, String message) {
    WsActionResp restResponse = new WsActionResp();
    restResponse.setCode(failCode);
    restResponse.setMessage(message);
    return restResponse;
  }

}