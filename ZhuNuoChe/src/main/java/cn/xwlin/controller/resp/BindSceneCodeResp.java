package cn.xwlin.controller.resp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BindSceneCodeResp {
  private Boolean bindSuccess;

  private String failReason;
}
