package cn.xwlin.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserInfo {

    private Long id;


    private String openId;


    private Integer qrCodeId;

    private String sceneCode;


    private String wxName;


    private String mobilePhone;


    private Integer wxNoticeFlag;


    private Integer smsNoticeFlag;


    private Integer telNoticeFlag;


    private String carNum;


    private Integer newEnergyFlag;


    private String scanNotice;


    private Date created;

}