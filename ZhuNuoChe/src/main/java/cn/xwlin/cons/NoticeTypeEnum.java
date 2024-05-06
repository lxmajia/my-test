package cn.xwlin.cons;

import lombok.Getter;

@Getter
public enum NoticeTypeEnum {
  /**
   * 二维码状态
   */
  WX(1, "微信消息"),
  SMS(2, "短信"),
  TEL(-3, "电话"),

  ;

  private Integer status;
  private String desc;

  NoticeTypeEnum(Integer status, String desc) {
    this.status = status;
    this.desc = desc;
  }
}
