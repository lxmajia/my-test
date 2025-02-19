package cn.xwlin.rcgame.service.crawl;

import cn.xwlin.rcgame.cons.EnumGameEventType;
import cn.xwlin.rcgame.cons.EnumPlayStatus;
import cn.xwlin.rcgame.cons.PositionTypeFlag;
import cn.xwlin.rcgame.dao.*;
import cn.xwlin.rcgame.entity.*;
import cn.xwlin.rcgame.util.DateUtil;
import com.google.common.collect.Lists;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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
  @Autowired
  private GameSeasonPlayerMapper gameSeasonPlayerMapper;
  @Autowired
  private GameInfoPlayerMapper gameInfoPlayerMapper;
  @Autowired
  private GameInfoEventMapper gameInfoEventMapper;


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


  @Transactional
  public String getYearGameInfo(Integer gameId) {
    GameInfoTitan gameInfoTitan = gameInfoTitanMapper.selectByPrimaryKey(gameId);
    if (gameInfoTitan == null) {
      return "gameId:" + gameId + ":Failed:找不到Titan关系映射";
    }
    GameInfo gameInfo = gameInfoMapper.selectByPrimaryKey(gameId);
    if (gameInfo == null) {
      return "gameId:" + gameId + ":Failed:找不到GameInfo数据";
    }
    if (gameInfo.getPositionType() == null) {
      gameInfo.setPositionType(0);
    }
    if ((gameInfo.getPositionType() & PositionTypeFlag.hasInitDate) != 0) {
      return "gameId:" + gameId + ":Failed:已经处理过了";
    }

    String url = "https://live.titan007.com/detail/" + gameInfoTitan.getTitanGameId() + "cn.htm";
    OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder().url(url) // 设置请求的URL
            .build();
    String responseBody = null;
    try {
      Response response = client.newCall(request).execute();
      if (!response.isSuccessful()) {
        return "gameId:" + gameId + ":Failed:Http请求失败";
      }
      responseBody = response.body().string();
    } catch (Exception e) {
      e.printStackTrace();
      return "gameId:" + gameId + ":Failed:Http请求失败";
    }

    // 处理之前，先把该比赛的数据删了
    gameInfoPlayerMapper.deleteByGameId(gameId);
    gameInfoEventMapper.deleteByGameId(gameId);

    Document document = Jsoup.parse(responseBody);
    {
      // 处理首发
      Element startPlayer = document.select("div[class=plays]").get(0);
      Elements homeSelect = startPlayer.select("div[class=home]");
      if (homeSelect.isEmpty()) {
        homeSelect = startPlayer.select("div[class=home five]");
      }

      Elements guestSelect = startPlayer.select("div[class=guest]");
      if (guestSelect.isEmpty()) {
        guestSelect = startPlayer.select("div[class=guest five]");
      }

      Elements mainPlayerList = homeSelect.get(0).select("div[class=play]");
      Elements guestPlayerList = guestSelect.get(0).select("div[class=play]");
      List<GameInfoPlayer> mainPlayerList1 = new ArrayList<>();
      for (Element element : mainPlayerList) {
        GameInfoPlayer gameInfoPlayer = buildGameInfoPlayer(element, gameInfo.getGameSeasonId(), gameInfo.getId(), gameInfo.getMainClubId(), true);
        mainPlayerList1.add(gameInfoPlayer);
      }
      createPlayerAndEvent(mainPlayerList1);

      List<GameInfoPlayer> guestPlayerList1 = new ArrayList<>();
      for (Element element : guestPlayerList) {
        GameInfoPlayer gameInfoPlayer = buildGameInfoPlayer(element, gameInfo.getGameSeasonId(), gameInfo.getId(), gameInfo.getGuestClubId(), true);
        guestPlayerList1.add(gameInfoPlayer);
      }
      createPlayerAndEvent(guestPlayerList1);
      System.out.println("首发处理完毕:" + gameId);
    }

    {
      // 处理替补
      Element startPlayer = document.select("div[class=backupPlay backupPlay2]").get(0);
      Elements mainPlayerList = startPlayer.select("div[class=home]").get(0).select("div[class=play]");
      Elements guestPlayerList = startPlayer.select("div[class=guest]").get(0).select("div[class=play]");
      List<GameInfoPlayer> mainPlayerList1 = new ArrayList<>();
      for (Element element : mainPlayerList) {
        GameInfoPlayer gameInfoPlayer = buildGameInfoPlayer(element, gameInfo.getGameSeasonId(), gameInfo.getId(), gameInfo.getMainClubId(), false);
        mainPlayerList1.add(gameInfoPlayer);
      }
      createPlayerAndEvent(mainPlayerList1);
      List<GameInfoPlayer> guestPlayerList1 = new ArrayList<>();
      for (Element element : guestPlayerList) {
        GameInfoPlayer gameInfoPlayer = buildGameInfoPlayer(element, gameInfo.getGameSeasonId(), gameInfo.getId(), gameInfo.getGuestClubId(), false);
        guestPlayerList1.add(gameInfoPlayer);
      }
      createPlayerAndEvent(guestPlayerList1);
      System.out.println("替补处理完毕:" + gameId);
    }
    gameInfo.setPositionType(gameInfo.getPositionType() | PositionTypeFlag.hasInitDate);
    gameInfoMapper.updateByPrimaryKeySelective(gameInfo);

    return "gameId:" + gameId + ":SUCCESS";
  }


  private void createPlayerAndEvent(List<GameInfoPlayer> mainPlayerList) {
    for (GameInfoPlayer gameInfoPlayer : mainPlayerList) {
      GameSeasonPlayer gameSeasonPlayer = gameSeasonPlayerMapper.selectBySeasonAndNum(gameInfoPlayer.getSeasonId(), gameInfoPlayer.getClubId(), gameInfoPlayer.getSeasonWearNum());
      if (gameSeasonPlayer == null) {
        gameSeasonPlayer = new GameSeasonPlayer();
        gameSeasonPlayer.setSeasonId(gameInfoPlayer.getSeasonId());
        gameSeasonPlayer.setClubId(gameInfoPlayer.getClubId());
        gameSeasonPlayer.setSeasonWearNum(gameInfoPlayer.getSeasonWearNum());
        gameSeasonPlayer.setHeight(gameInfoPlayer.getHeight());
        gameSeasonPlayer.setBirthday(gameInfoPlayer.getBirthday());
        gameSeasonPlayer.setNational(gameInfoPlayer.getNational());
        gameSeasonPlayer.setCnName(gameInfoPlayer.getName());
        gameSeasonPlayer.setTitanDetailUrl(gameInfoPlayer.getDetailUrl());
        gameSeasonPlayerMapper.insertSelective(gameSeasonPlayer);
      }
      GameInfoPlayer gameInfoPlayer1 = gameInfoPlayerMapper.selectBySeasonAndNum(gameInfoPlayer.getSeasonPlayerId(), gameInfoPlayer.getClubId(), gameInfoPlayer.getSeasonWearNum());
      if (gameInfoPlayer1 == null) {
        gameInfoPlayer1 = gameInfoPlayer;
        gameInfoPlayer1.setSeasonPlayerId(gameSeasonPlayer.getId());
        gameInfoPlayerMapper.insert(gameInfoPlayer1);
      }
      if (!CollectionUtils.isEmpty(gameInfoPlayer.getEventList())) {
        for (GameInfoEvent gameInfoEvent : gameInfoPlayer.getEventList()) {
          GameInfoEvent gameInfoEvent1 = gameInfoEventMapper.selectByGamePlayerEvent(gameInfoPlayer.getGameId(), gameInfoPlayer1.getId(), gameInfoEvent.getEventType());
          if (gameInfoEvent1 == null) {
            gameInfoEvent.setSeasonId(gameInfoPlayer1.getSeasonId());
            gameInfoEvent.setClubId(gameInfoPlayer1.getClubId());
            gameInfoEvent.setGameInfoPlayerId(gameInfoPlayer1.getId());
            gameInfoEventMapper.insertSelective(gameInfoEvent);
          }
        }
      }


    }
  }


  private static GameInfoPlayer buildGameInfoPlayer(Element element, Integer seasonId, Integer gameId, Integer clubId, boolean isStartUp) {
    String onmouseover = element.attr("onmouseover");
    String name;
    String num;
    if (StringUtils.hasLength(onmouseover)) {
      num = element.select("span").get(0).select("span").get(0).select("i").get(0).ownText();
      name = element.select("div").get(0).select("a").get(0).ownText();
    } else {
      String numName = element.select("div").get(0).select("a").get(0).ownText();
      String[] split = numName.split(" ");

      num = split[0];
      name = split[1];
    }

    String detailUrl = element.select("div").get(0).select("a").get(0).attr("href");


    Elements select = element.select("ul").get(0).select("li");
    String birth = null, height = null, nation = null;
    for (Element element1 : select) {
      String s = element1.ownText();
      if (s.contains("生日")) {
        birth = s.replace("生日：", "");
      } else if (s.contains("身高")) {
        height = s.replace("身高：", "").replace("(CM)", "");
      } else if (s.contains("国籍")) {
        nation = s.replace("国籍：", "");
      }
    }

    GameInfoPlayer player = new GameInfoPlayer();
    player.setSeasonId(seasonId);
    player.setGameId(gameId);
    player.setClubId(clubId);
    player.setSeasonWearNum(Integer.parseInt(num));
    player.setName(name);
    player.setDetailUrl(detailUrl);

    if (birth != null) {
      try {
        player.setBirthday(DateUtil.parseDate(birth, "yyyy-MM-dd"));
      } catch (Exception e) {
      }
    }

    if (height != null) {
      try {
        player.setHeight(Integer.parseInt(height));
      } catch (Exception e) {
      }
    }
    if (nation != null) {
      try {
        player.setNational(nation);
      } catch (Exception e) {
      }
    }

    // 处理事件
    Elements eveltList = element.select("div[id^=playerTech_]");
    if (!eveltList.isEmpty()) {
      List<GameInfoEvent> eventList = Lists.newArrayList();
      Elements eventImg = eveltList.get(0).select("img");
      for (Element oneEvent : eventImg) {
        String eventMinute = oneEvent.attr("alt").replace("'", "");
        String src = oneEvent.attr("src");
        EnumGameEventType enumGameEventType = EnumGameEventType.filterTitanEvent(src);
        if (enumGameEventType != null) {
          GameInfoEvent gameInfoEvent = new GameInfoEvent();
          gameInfoEvent.setGameId(gameId);
          gameInfoEvent.setEventType(enumGameEventType.getStatus());
          gameInfoEvent.setEventDesc(enumGameEventType.getDesc());
          if (!eventMinute.isEmpty()) {
            if (eventMinute.contains("+")) {
              String[] split = eventMinute.split("\\+");
              gameInfoEvent.setGameMinute(Integer.parseInt(split[0]));
              gameInfoEvent.setGameMinuteAdd(Integer.parseInt(split[1]));
            } else {
              gameInfoEvent.setGameMinute(Integer.parseInt(eventMinute));
              gameInfoEvent.setGameMinuteAdd(0);
            }
          }
          eventList.add(gameInfoEvent);
        }
      }
      player.setEventList(eventList);
    }

    // 处理上下场事件
    if (isStartUp) {
      player.setStartPlayer(1);
      player.setUpMinute(0);
      player.setOffMinute(90);
    } else {
      player.setStartPlayer(0);
      player.setUpMinute(0);
      player.setOffMinute(0);
    }


    if (!CollectionUtils.isEmpty(player.getEventList())) {
      // 处理换入
      for (GameInfoEvent gameInfoEvent : player.getEventList()) {
        if (EnumGameEventType.TransIn.getStatus() == gameInfoEvent.getEventType()) {
          player.setUpMinute(gameInfoEvent.getGameMinute());
          player.setOffMinute(90);
        }
      }
      // 处理换出
      for (GameInfoEvent gameInfoEvent : player.getEventList()) {
        if (EnumGameEventType.TransOut.getStatus() == gameInfoEvent.getEventType()) {
          player.setOffMinute(gameInfoEvent.getGameMinute());
        }
      }
    }
    player.setGameMinute(player.getOffMinute() - player.getUpMinute());

    return player;
  }

  public static void main(String[] args) {
    new Titan007Service().getYearGameInfo(2376991);
  }
}
