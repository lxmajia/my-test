package cn.xwlin.controller.resp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScanQueryResp {
  /**
   * 是否已经绑定了
   */
  private Boolean isBind;
  // 码
  private String sceneCode;
  // 绑定用户的UserId
  private Long bindUserId;
  // 是否微信通知
  private Integer wxNoticeFlag;
  // 是否短信通知
  private Integer smsNoticeFlag;
  //是否开启电话通知
  private Integer telNoticeFlag;
  //车牌
  private String carNum;
  //是否新能源
  private Integer newEnergyFlag;
  // 提示信息
  private String scanNotice;
}
