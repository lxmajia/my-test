package cn.xwlin.rcgame.service.crawl;

import cn.xwlin.rcgame.cons.EnumPlayStatus;
import cn.xwlin.rcgame.dao.ClubInfoMapper;
import cn.xwlin.rcgame.dao.GameInfoMapper;
import cn.xwlin.rcgame.dao.GameInfoTitanMapper;
import cn.xwlin.rcgame.entity.ClubInfo;
import cn.xwlin.rcgame.entity.GameInfo;
import cn.xwlin.rcgame.entity.GameInfoTitan;
import cn.xwlin.rcgame.util.DateUtil;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Date;

/**
 * @author xiang.liao
 * @create 2025/2/18
 */
@Service
public class Titan007Service {

  @Autowired
  private ClubInfoMapper clubInfoMapper;
  @Autowired
  private GameInfoMapper gameInfoMapper;
  @Autowired
  private GameInfoTitanMapper gameInfoTitanMapper;


  @Transactional
  public String getYearGameInfo(Integer year, Integer typeId, Integer seasonId) {
    String url = "https://zq.titan007.com/cn/team/TeamScheByYearAjax.aspx?TeamID=40347&y=" + year + "&flesh=0.9528587123074899";

    OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder().url(url) // 设置请求的URL
            .build();
    try {
      Response response = client.newCall(request).execute();
      if (response.isSuccessful()) {
        String responseBody = response.body().string();
        responseBody = responseBody.trim().replace("", "").replace("var teamPageData = [", "").replace("];", "");

        String[] split = responseBody.split("],\\[");
        for (String s : split) {
          s = s.replace("[", "").replace("[", "").replace("'", "");
          String[] oneLine = s.split(",");
          String titanGameId = oneLine[0];//2262992
          String gameTime = oneLine[3];//2022/01/12 15:30
          String fullScore = oneLine[6];//1-0
          String halfScore = oneLine[7];//1-0
          String matchName = oneLine[8];//1-0
          String home = oneLine[11];//homeName
          String guest = oneLine[14];//guestName

          if (!matchName.equals("中超")) {
            continue;
          }

          ClubInfo homeClubInfo = clubInfoMapper.selectByShowName(home);
          if (homeClubInfo == null) {
            homeClubInfo = new ClubInfo();
            homeClubInfo.setName(home);
            homeClubInfo.setShowName(home);
            clubInfoMapper.insertSelective(homeClubInfo);
          }

          ClubInfo guestClubInfo = clubInfoMapper.selectByShowName(guest);
          if (guestClubInfo == null) {
            guestClubInfo = new ClubInfo();
            guestClubInfo.setName(guest);
            guestClubInfo.setShowName(guest);
            clubInfoMapper.insertSelective(guestClubInfo);
          }

          GameInfo gameInfo = new GameInfo();
          gameInfo.setGameTypeId(typeId);
          gameInfo.setGameSeasonId(seasonId);
          gameInfo.setGameNameDesc(home + " VS " + guest);
          gameInfo.setMainClubId(homeClubInfo.getId());
          gameInfo.setMainClubName(homeClubInfo.getShowName());
          gameInfo.setGuestClubId(guestClubInfo.getId());
          gameInfo.setGuestClubName(guestClubInfo.getShowName());
          //2022/01/12 15:30
          gameInfo.setMatchTime(DateUtil.parseDate(gameTime, "yyyy/MM/dd HH:mm"));
          if (gameInfo.getMatchTime().after(new Date())) {
            gameInfo.setMatchStatus(EnumPlayStatus.NO_START.getStatus());
          } else {
            gameInfo.setMatchStatus(EnumPlayStatus.FINISHED.getStatus());
            gameInfo.setRealMatchTime(gameInfo.getMatchTime());
          }
          if (halfScore != null && !halfScore.isEmpty()) {
            String[] halfScoreSplit = halfScore.split("-");
            gameInfo.setHalfMainScore(Integer.parseInt(halfScoreSplit[0]));
            gameInfo.setHalfGuestScore(Integer.parseInt(halfScoreSplit[1]));
          }

          if (fullScore != null && !fullScore.isEmpty()) {
            String[] fullScoreSplit = fullScore.split("-");
            gameInfo.setFullMainScore(Integer.parseInt(fullScoreSplit[0]));
            gameInfo.setFullGuestScore(Integer.parseInt(fullScoreSplit[1]));
          }

          if (gameInfo.getFullMainScore() != null || gameInfo.getFullGuestScore() != null) {
            if (gameInfo.getFullMainScore() > gameInfo.getFullGuestScore()) {
              gameInfo.setMainPoint(3);
              gameInfo.setGuestPoint(0);
            } else if (gameInfo.getFullMainScore() < gameInfo.getFullGuestScore()) {
              gameInfo.setMainPoint(0);
              gameInfo.setGuestPoint(3);
            } else {
              gameInfo.setMainPoint(1);
              gameInfo.setGuestPoint(1);
            }
          }
          gameInfoMapper.insert(gameInfo);

          GameInfoTitan gameInfoTitan = new GameInfoTitan();
          gameInfoTitan.setId(gameInfo.getId());
          gameInfoTitan.setGameId(gameInfo.getId());
          gameInfoTitan.setTitanGameId(titanGameId);
          gameInfoTitanMapper.insertSelective(gameInfoTitan);
        }
        return "success";
      } else {
        // 请求失败，处理错误
        throw new IOException("Unexpected code " + response);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "";
  }


  public String getYearGameInfo(Integer gameId) {
    String url = "https://live.titan007.com/PlayerTech.aspx?ID=" + gameId + "&l=0";
    OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder().url(url) // 设置请求的URL
            .build();
    try {
      Response response = client.newCall(request).execute();
      if (response.isSuccessful()) {
        String responseBody = response.body().string();
        System.out.println(responseBody);
      }
    } catch (Exception e) {

    }
    return "";
  }

  public static void main(String[] args) {
    new Titan007Service().getYearGameInfo(2376991);
  }
}
