package cn.xwlin.rcgame.crawldto;

import lombok.Data;

import java.util.Date;

@Data
public class ZbbPlayer {

    private Long id;

    private String name;

    private String name_en;
    private Integer number;
    private String position;
    private String nationality;
    private Date birthday;
    private Date join_time;
    private Date contract_time;
    private String avatar;
    private String height;
    private String weight;
}
