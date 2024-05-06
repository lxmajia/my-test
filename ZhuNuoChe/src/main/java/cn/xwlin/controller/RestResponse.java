package cn.xwlin.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestResponse {
  private Boolean success;
  private String message;
  private Object data;

  public RestResponse() {

  }

  public static RestResponse succuess() {
    RestResponse restResponse = new RestResponse();
    restResponse.setSuccess(true);

    return restResponse;
  }

  public static RestResponse succuess(Object data) {
    RestResponse restResponse = new RestResponse();
    restResponse.setSuccess(true);
    restResponse.setData(data);

    return restResponse;
  }

  public static RestResponse fail() {
    RestResponse restResponse = new RestResponse();
    restResponse.setSuccess(false);
    return restResponse;
  }


  public static RestResponse fail(String message) {
    RestResponse restResponse = new RestResponse();
    restResponse.setSuccess(false);
    restResponse.setMessage(message);

    return restResponse;
  }

}