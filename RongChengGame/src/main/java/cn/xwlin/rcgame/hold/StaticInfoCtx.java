package cn.xwlin.rcgame.hold;

import cn.xwlin.rcgame.dao.ClubInfoMapper;
import cn.xwlin.rcgame.entity.ClubInfo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiang.liao
 * @create 2023/9/1
 */
@Component
public class StaticInfoCtx implements InitializingBean {

  @Autowired
  private ClubInfoMapper clubInfoMapper;

  private static ClubInfo mainClub = null;
  private static Map<Long, ClubInfo> clubInfoMap = new HashMap<>();


  @Override
  public void afterPropertiesSet() {
    List<ClubInfo> clubInfos = clubInfoMapper.selectAll();
    for (ClubInfo clubInfo : clubInfos) {
      if (clubInfo.getIsMain() && mainClub == null) {
        mainClub = clubInfo;
      }
      clubInfoMap.put(clubInfo.getId(), clubInfo);
    }
  }

  public static ClubInfo getMainClub() {
    return mainClub;
  }

  public static ClubInfo getClubInfo(Long clubId) {
    return clubInfoMap.get(clubId);
  }
}
