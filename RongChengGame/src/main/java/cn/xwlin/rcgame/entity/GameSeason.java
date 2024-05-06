package cn.xwlin.rcgame.entity;

import com.mybatisflex.annotation.Table;
import lombok.Data;

import java.util.Date;

@Data
@Table("game_season")
public class GameSeason {
    private Long id;

    private Long gameKindId;

    private Date startTime;

    private Date endTime;

    private String seasonName;

    private String seasonShortName;

    private String startYear;

    private String endYear;

    private Date createTime;

    private Date operateTime;
}