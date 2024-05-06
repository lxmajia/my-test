package cn.xwlin.rcgame.entity;

import com.mybatisflex.annotation.Table;
import lombok.Data;

import java.util.Date;

@Data
@Table("game_play_event")
public class GamePlayEvent {
    private Long id;
    private Long playId;
    private Integer eventType;
    private String eventName;

    private Long playerId;

    private String playerName;

    private Integer eventMins;
    private Integer addMins;

    private Date createTime;

    private Date operateTime;

    private String zbbPlayerId;
}