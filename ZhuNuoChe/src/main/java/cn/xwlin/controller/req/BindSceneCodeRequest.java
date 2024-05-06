package cn.xwlin.controller.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BindSceneCodeRequest {
  // WxJsCode用于获取openid
  private String jsCode;
  // 码
  private String sceneCode;
  // 电话
  private String mobileNo;
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
