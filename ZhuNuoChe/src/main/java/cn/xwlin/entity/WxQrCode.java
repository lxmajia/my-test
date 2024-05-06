package cn.xwlin.entity;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WxQrCode {
   
    private Long id;

  
    private String sceneCode;

   
    private String viewUrl;

   
    private String pageUrl;

    private String openId;

    private Long userId;

  
    private Date userBindTime;

    private Integer qrCodeStatus;

    private Date created;

}