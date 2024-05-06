package cn.xwlin.rcgame.entity;

import com.mybatisflex.annotation.Table;
import lombok.Data;

import java.util.Date;

@Data
@Table("club_info")
public class ClubInfo {
    private Long id;

    private String cnName;

    private String enName;

    private String shortName;

    private String logoUrl;

    private String cityName;

    private String siteName;

    private Date bornTime;

    private Boolean isMain;

    private String jingDu;

    private String weiDu;

    private Date createTime;

    private Date operateTime;

    private String zbbTeamId;
    private String zbbLogoUrl;

}