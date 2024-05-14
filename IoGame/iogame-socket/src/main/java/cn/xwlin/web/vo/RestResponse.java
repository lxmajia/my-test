package cn.xwlin.web.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestResponse<T> {
  private int code;
  private String message;
  private T body;

  public RestResponse() {

  }

  public static RestResponse succuess() {
    RestResponse restResponse = new RestResponse();
    restResponse.setCode(0);
    return restResponse;
  }

  public static <T> RestResponse succuess(T body) {
    RestResponse restResponse = new RestResponse();
    restResponse.setCode(0);
    restResponse.setBody(body);

    return restResponse;
  }

  public static RestResponse fail(int failCode) {
    RestResponse restResponse = new RestResponse();
    restResponse.setCode(failCode);
    return restResponse;
  }


  public static RestResponse fail(int failCode, String message) {
    RestResponse restResponse = new RestResponse();
    restResponse.setCode(failCode);
    restResponse.setMessage(message);
    return restResponse;
  }

}