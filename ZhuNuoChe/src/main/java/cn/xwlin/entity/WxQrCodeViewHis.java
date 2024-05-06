package cn.xwlin.entity;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WxQrCodeViewHis {

    private Integer id;


    private String sceneCode;


    private String viewOpenId;


    private Date created;

}