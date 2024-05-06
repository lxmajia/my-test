package cn.xwlin.rcgame.crawldto;

import lombok.Data;

@Data
public class ZbbMatchPlayer {
    private String player_id;
    private String player_name_cn;
    // z首发，t替补
    private String status;
    private String up_time;
    private String down_time;
    private Integer goal;
    private String position_long_cn;
    private ZbbMatchPlayerCard card;
}
