package cn.xwlin.controller.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScanQueryRequest {
  private String openId;
  private String sceneCode;
}
