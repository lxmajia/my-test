package cn.xwlin.rcgame.entity;

import com.mybatisflex.annotation.Table;
import lombok.Data;

import java.util.Date;

@Data
@Table("game_season_player")
public class GameSeasonPlayer {
  private Long id;

  private Long seasonId;

  private Long playerId;

  private Boolean fullSeason;

  private Date joinTime;

  private Date leaveTime;

  private Date createTime;
}