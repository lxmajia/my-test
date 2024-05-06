package cn.xwlin.rcgame.crawldto;

import lombok.Data;

import java.util.Date;

@Data
public class ZbbMatchInfo {

    private Long saishi_id;
    // 3完了，1未开始
    private String state;
    private Integer round;
    private String hteam_name;
    private String hteam_id;
    private String gteam_name;
    private String gteam_id;
    private Date games_time;
    private Integer hscore;
    private Integer gscore;
}
