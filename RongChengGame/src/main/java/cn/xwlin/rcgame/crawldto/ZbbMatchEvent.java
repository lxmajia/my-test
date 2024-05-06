package cn.xwlin.rcgame.crawldto;

import lombok.Data;

import java.util.Date;

@Data
public class ZbbMatchEvent {

    // 6865
    private String sl_team_id;
    private String time;
    private String player_id;
    private String player_name_cn;
    /**
     * 1 进球
     * 2 乌龙球
     * 3 黄牌
     * 4 两黄罚下
     * 5 红牌
     * 6 点球罚进
     * diy_20 助攻
     * 14 换下
     * 15 换上
     * 16 受伤换下
     * 34 造点球
     * 36 点球罚丢
     */
    private String event_code;
}
