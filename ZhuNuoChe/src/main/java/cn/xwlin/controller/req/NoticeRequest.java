package cn.xwlin.controller.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeRequest {
  // 谁发起的openid
  private String callerOpenId;
  // 接收方
  private Long bindUserId;
  // 码
  private String sceneCode;
  /**
   * 通知方式
   *
   * @see per.lx.cons.NoticeTypeEnum
   */
  private Integer noticeType;
}
