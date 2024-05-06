package cn.xwlin.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrderInfo {
    private Long id;

    private String goodName;

    private Integer userId;

    private Integer status;

    private Date crtTime;
}