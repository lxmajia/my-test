package cn.xwlin.rcgame.service;

import cn.xwlin.rcgame.cons.EnumGameEventType;
import cn.xwlin.rcgame.cons.EnumIsFirst;
import cn.xwlin.rcgame.cons.EnumPlayStatus;
import cn.xwlin.rcgame.cons.EnumPlayerStatus;
import cn.xwlin.rcgame.controller.response.HttpResp;
import cn.xwlin.rcgame.controller.response.ToDayGame;
import cn.xwlin.rcgame.dao.GameInfoEventMapper;
import cn.xwlin.rcgame.dao.GameInfoMapper;
import cn.xwlin.rcgame.dao.GameInfoPlayerMapper;
import cn.xwlin.rcgame.dao.GameSeasonPlayerMapper;
import cn.xwlin.rcgame.entity.GameInfo;
import cn.xwlin.rcgame.entity.GameInfoEvent;
import cn.xwlin.rcgame.entity.GameInfoPlayer;
import cn.xwlin.rcgame.entity.GameSeasonPlayer;
import cn.xwlin.rcgame.util.DateUtil;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author xiang.liao
 * @create 2025/2/21
 */
@Service
public class OperateGameService {

  @Autowired
  private GameInfoMapper gameInfoMapper;
  @Autowired
  private GameInfoPlayerMapper gameInfoPlayerMapper;
  @Autowired
  private GameSeasonPlayerMapper gameSeasonPlayerMapper;
  @Autowired
  private GameInfoEventMapper gameInfoEventMapper;
  private static final Integer RONG_CHENG_CLUB_ID = 7;


  public HttpResp<ToDayGame> todayGame() {
    GameInfo gameInfo = gameInfoMapper.getToDayGame();
    if (gameInfo == null) {
      return HttpResp.fail(1, "Failed:今天没有比赛");
    }
    ToDayGame toDayGame = new ToDayGame();
    toDayGame.setGameName(gameInfo.getGameNameDesc());
    toDayGame.setGameId(gameInfo.getId());
    toDayGame.setGameTime(DateUtil.format(gameInfo.getMatchTime(), "yyyy-MM-dd HH:mm:ss"));
    return HttpResp.success(toDayGame);
  }

  @Transactional
  public HttpResp setFirstPlayer(Integer gameId, String firstPlayer) {
    GameInfo gameInfo = gameInfoMapper.selectByPrimaryKey(gameId);
    if (gameInfo == null) {
      return HttpResp.fail(1, "gameId:" + gameId + ":Failed:找不到GameInfo数据");
    }
    String[] split = firstPlayer.split(",");
    if (split.length != 11) {
      return HttpResp.fail(1, "firstPlayer:" + firstPlayer + ":Failed:不够11个人");
    }
    Set<Integer> collect = Stream.of(split).map(Integer::parseInt).collect(Collectors.toSet());
    if (collect.size() != 11) {
      return HttpResp.fail(1, "firstPlayer:" + firstPlayer + ":Failed:不够11个人");
    }
    List<GameSeasonPlayer> gameSeasonPlayers = gameSeasonPlayerMapper.selectBySeasonAndNumList(gameInfo.getGameSeasonId(), RONG_CHENG_CLUB_ID, new ArrayList<>(collect));
    if (gameSeasonPlayers.size() < collect.size()) {
      return HttpResp.fail(1, "firstPlayer:" + firstPlayer + ":Failed:有号码找不到");
    }
    int i = gameInfoPlayerMapper.deleteByGameIdAndStartPlayer(gameId, EnumIsFirst.YES.getStatus());

    for (GameSeasonPlayer gameSeasonPlayer : gameSeasonPlayers) {
      GameInfoPlayer player = new GameInfoPlayer();
      player.setSeasonId(gameInfo.getGameSeasonId());
      player.setGameId(gameId);
      player.setClubId(RONG_CHENG_CLUB_ID);
      player.setSeasonPlayerId(gameSeasonPlayer.getId());
      player.setSeasonWearNum(gameSeasonPlayer.getSeasonWearNum());
      player.setBirthday(gameSeasonPlayer.getBirthday());
      player.setHeight(gameSeasonPlayer.getHeight());
      player.setNational(gameSeasonPlayer.getNational());
      player.setStartPlayer(EnumIsFirst.YES.getStatus());
      player.setUpMinute(0);
      player.setOffMinute(90);
      player.setGameMinute(90);
      gameInfoPlayerMapper.insertSelective(player);
    }
    return HttpResp.success();
  }

  @Transactional
  public HttpResp setSubPlayer(Integer gameId, String subPlayer) {
    GameInfo gameInfo = gameInfoMapper.selectByPrimaryKey(gameId);
    if (gameInfo == null) {
      return HttpResp.fail(1, "gameId:" + gameId + ":Failed:找不到GameInfo数据");
    }
    String[] split = subPlayer.split(",");
    Set<Integer> collect = Stream.of(split).map(Integer::parseInt).collect(Collectors.toSet());
    List<GameSeasonPlayer> gameSeasonPlayers = gameSeasonPlayerMapper.selectBySeasonAndNumList(gameInfo.getGameSeasonId(), RONG_CHENG_CLUB_ID, new ArrayList<>(collect));
    if (gameSeasonPlayers.size() < collect.size()) {
      return HttpResp.fail(1, "firstPlayer:" + subPlayer + ":Failed:有号码找不到");
    }
    int i = gameInfoPlayerMapper.deleteByGameIdAndStartPlayer(gameId, EnumIsFirst.NO.getStatus());

    for (GameSeasonPlayer gameSeasonPlayer : gameSeasonPlayers) {
      GameInfoPlayer player = new GameInfoPlayer();
      player.setSeasonId(gameInfo.getGameSeasonId());
      player.setGameId(gameId);
      player.setClubId(RONG_CHENG_CLUB_ID);
      player.setSeasonPlayerId(gameSeasonPlayer.getId());
      player.setSeasonWearNum(gameSeasonPlayer.getSeasonWearNum());
      player.setBirthday(gameSeasonPlayer.getBirthday());
      player.setHeight(gameSeasonPlayer.getHeight());
      player.setNational(gameSeasonPlayer.getNational());
      player.setStartPlayer(EnumIsFirst.NO.getStatus());
      player.setUpMinute(0);
      player.setOffMinute(0);
      player.setGameMinute(0);
      gameInfoPlayerMapper.insertSelective(player);
    }
    return HttpResp.success();
  }

  public HttpResp startGame(Integer gameId) {
    GameInfo gameInfo = gameInfoMapper.selectByPrimaryKey(gameId);
    if (gameInfo == null) {
      return HttpResp.fail(1, "gameId:" + gameId + ":Failed:找不到GameInfo数据");
    }
    GameInfo updateDb = new GameInfo();
    updateDb.setId(gameId);
    updateDb.setMatchStatus(EnumPlayStatus.DOING.getStatus());
    gameInfoMapper.updateByPrimaryKeySelective(updateDb);
    return HttpResp.success();
  }

  @Transactional
  public HttpResp endGame(Integer gameId) {
    GameInfo gameInfo = gameInfoMapper.selectByPrimaryKey(gameId);
    if (gameInfo == null) {
      return HttpResp.fail(1, "gameId:" + gameId + ":Failed:找不到GameInfo数据");
    }
    GameInfo updateDb = new GameInfo();
    updateDb.setId(gameId);
    updateDb.setMatchStatus(EnumPlayStatus.FINISHED.getStatus());
    gameInfoMapper.updateByPrimaryKeySelective(updateDb);

    gameInfoPlayerMapper.updateGameMinute(gameId);
    return HttpResp.success();
  }

  @Transactional
  public HttpResp event(Integer gameId, Integer gamePlayerId, Integer eventType, Integer minute, Integer minuteAdd) {
    GameInfo gameInfo = gameInfoMapper.selectByPrimaryKey(gameId);
    if (gameInfo == null) {
      return HttpResp.fail(1, "gameId:" + gameId + ":Failed:找不到GameInfo数据");
    }
    GameInfoPlayer gameInfoPlayer = gameInfoPlayerMapper.selectByPrimaryKey(gamePlayerId);
    if (gameInfoPlayer == null) {
      return HttpResp.fail(1, "gamePlayerId:" + gamePlayerId + ":Failed:找不到GameInfoPlayer数据");
    }

    EnumGameEventType eventType1 = EnumGameEventType.getEventType(eventType);
    if (eventType1 == null) {
      return HttpResp.fail(1, "eventType:" + eventType + ":Failed:找不到对应eventType数据");
    }
    GameInfoEvent gameInfoEvent = new GameInfoEvent();
    gameInfoEvent.setSeasonId(gameInfo.getGameSeasonId());
    gameInfoEvent.setClubId(RONG_CHENG_CLUB_ID);
    gameInfoEvent.setGameId(gameId);
    gameInfoEvent.setGameInfoPlayerId(gamePlayerId);
    gameInfoEvent.setEventType(eventType1.getStatus());
    gameInfoEvent.setEventDesc(eventType1.getDesc());
    gameInfoEvent.setGameMinute(minute);
    if ((minute == 45 || minute == 90) && minuteAdd != null && minuteAdd > 0) {
      gameInfoEvent.setGameMinuteAdd(minuteAdd);
    }
    gameInfoEventMapper.insertSelective(gameInfoEvent);

    // 换入换出，把出场时间设置了
    if (EnumGameEventType.TransOut.equals(eventType1)) {
      GameInfoPlayer updateGamePlayer = new GameInfoPlayer();
      updateGamePlayer.setId(gamePlayerId);
      updateGamePlayer.setOffMinute(minute);
      updateGamePlayer.setGameMinute(minute - gameInfoPlayer.getUpMinute());
      gameInfoPlayerMapper.updateByPrimaryKeySelective(updateGamePlayer);
    } else if (EnumGameEventType.TransIn.equals(eventType1)) {
      GameInfoPlayer updateGamePlayer = new GameInfoPlayer();
      updateGamePlayer.setId(gamePlayerId);
      updateGamePlayer.setUpMinute(minute);
      // 默认下场时间为90
      updateGamePlayer.setOffMinute(90);
      gameInfoPlayerMapper.updateByPrimaryKeySelective(updateGamePlayer);
    }
    return HttpResp.success();
  }
}
