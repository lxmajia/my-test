package cn.xwlin.rcgame.entity;

import com.mybatisflex.annotation.Table;
import lombok.Data;

import java.util.Date;

@Data
@Table("player_info")
public class PlayerInfo {
    private Long id;

    private String cnName;

    private String enName;

    private Integer tNum;

    private String tPosition;

    private String photoUrl;

    private String betterFoot;

    private Date birthday;

    private Date joinTime;

    private Date endTime;

    private String nation;

    private Integer status;

    private Date contactTime;

    private Date createTime;

    private Date operateTime;

    private String height;
    private String weight;


    // 直播吧头像地址
    private String zbbPhotoUrl;
    // 直播吧playerID
    private Long zbbPlayerId;
}