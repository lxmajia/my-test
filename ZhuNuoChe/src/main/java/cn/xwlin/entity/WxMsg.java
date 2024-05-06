package cn.xwlin.entity;


import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WxMsg {

    private Long id;

    private String sceneCode;


    private Long fromUserId;


    private String fromUserOpenId;


    private Long toUserId;


    private String toUserOpenId;


    private String templateId;


    private String templateData;


    private Integer sendStatus;


    private Date created;

}