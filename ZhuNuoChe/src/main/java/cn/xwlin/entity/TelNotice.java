package cn.xwlin.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TelNotice {

    private Long id;


    private String sceneCode;


    private Long fromUserId;

    private Long toUserId;


    private String toMobile;


    private Integer sendStatus;


    private Date created;
}