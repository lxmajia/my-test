package cn.xwlin.rcgame.entity;

import com.mybatisflex.annotation.Table;
import lombok.Data;

import java.util.Date;

@Data
@Table("game_kind")
public class GameKind {
  private Long id;

  private Integer gameType;

  private String gameName;

  private String gameLogo;

  private Date createTime;

  private Date operateTime;

}