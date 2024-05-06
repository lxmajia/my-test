package cn.xwlin.rcgame.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import lombok.Data;

import java.util.Date;

@Data
@Table("game_play_info")
public class GamePlayInfo {
    @Id
    private Long id;
    // 赛事类型
    private Long gameKindId;
    // 赛季类型
    private Long seasonId;
    // 轮次
    private Integer playSort;
    // 比赛名
    private String playName;
    // 是否主场
    private Integer isHome;
    // 主队Id
    private Long homeId;
    // 主队
    private String homeName;
    private Long guestId;
    // 客队
    private String guestName;
    // 比赛状态
    private Integer playStatus;
    // 开球时间
    private Date playTime;

    private String playCity;

    private String playSite;

    private Integer homeScore;

    private Integer guestScore;

    private Boolean hasAdd;

    private Integer addHomeScore;

    private Integer addGuestScore;

    private String cocheName;

    private Integer fansCount;

    private String mainRefer;

    private Boolean hasVar;

    private String varRefer;

    private Date createTime;

    private Date operateTime;

    private Long zbbMatchId;
    private String zbbHomeId;
    private String zbbGuestId;
}