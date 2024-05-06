package cn.xwlin.cons;

import lombok.Getter;

@Getter
public enum QrCodeStatus {
  /**
   * 二维码状态
   */
  INIT(0, "未使用"),
  NORMAL(1, "正常使用"),
  DELETE(-1, "失效"),
  NO_GENERATE(-2, "未生成Code"),

  ;

  private Integer status;
  private String desc;

  QrCodeStatus(Integer status, String desc) {
    this.status = status;
    this.desc = desc;
  }
}
