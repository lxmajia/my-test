package cn.xwlin.rcgame.entity;

import com.mybatisflex.annotation.Table;
import lombok.Data;

import java.util.Date;

@Data
@Table("game_play_player")
public class GamePlayPlayer {
    private Long id;

    private Long playId;

    private Long playerId;

    private String playerName;

    private Integer isFirst;

    private Integer isSubGo;

    private Long subGamePlayerId;

    private Integer inMins;

    private Integer outMins;

    private Integer totalMins;

    private Integer getGoalCount;

    private Integer yellowCardCount;

    private Integer redCardCount;

    private String position;

    private Date createTime;

    private Date operateTime;

    private String zbbPlayerId;
}