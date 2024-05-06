package cn.xwlin.rcgame.service;

import cn.xwlin.rcgame.cons.EnumEventType;
import cn.xwlin.rcgame.cons.EnumPlayStatus;
import cn.xwlin.rcgame.cons.EnumPlayerStatus;
import cn.xwlin.rcgame.crawldto.*;
import cn.xwlin.rcgame.dao.*;
import cn.xwlin.rcgame.entity.*;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.google.common.collect.Lists;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author xiang.liao
 * @create 2023/9/1
 */
@Service
public class InitDataService {
  @Autowired
  private PlayerInfoMapper playerInfoMapper;
  @Autowired
  private ClubInfoMapper clubInfoMapper;
  @Autowired
  private GamePlayInfoMapper gamePlayInfoMapper;
  @Autowired
  private GamePlayPlayerMapper gamePlayPlayerMapper;
  @Autowired
  private GamePlatEventMapper gamePlatEventMapper;
  @Autowired
  private OkHttpClient okHttpClient;

  public String initZbbPlayer() {
    String zbbPalyerInfo = getZbbPalyerInfo();
    ZbbPlayerInfo zbbPlayerInfo = JSONObject.parseObject(zbbPalyerInfo, ZbbPlayerInfo.class);

    List<ZbbPlayer> players = zbbPlayerInfo.getPlayers();

    List<PlayerInfo> dbPlayerInfoList = Lists.newArrayList();

    for (ZbbPlayer player : players) {
      PlayerInfo playerInfo = new PlayerInfo();
      playerInfo.setCnName(player.getName());
      playerInfo.setEnName(player.getName_en());
      playerInfo.setTNum(player.getNumber());
      playerInfo.setTPosition(player.getPosition());
      playerInfo.setBirthday(player.getBirthday());
      playerInfo.setJoinTime(player.getJoin_time());
      playerInfo.setEndTime(player.getContract_time());
      playerInfo.setNation(player.getNationality());
      playerInfo.setContactTime(player.getContract_time());
      playerInfo.setCreateTime(new Date());
      playerInfo.setOperateTime(new Date());
      playerInfo.setZbbPhotoUrl(player.getAvatar());
      playerInfo.setZbbPlayerId(player.getId());
      playerInfo.setHeight(player.getHeight());
      playerInfo.setWeight(player.getWeight());
      playerInfo.setStatus(EnumPlayerStatus.IN.getStatus());
      dbPlayerInfoList.add(playerInfo);
    }
    // int i = playerInfoMapper.insertBatch(dbPlayerInfoList);
    return "插入成功";
  }


  public String initClubInfo() {
    String clubInfoStr = getClubInfo();
    List<ZbbTeamInfo> zbbTeamInfos = JSONArray.parseArray(clubInfoStr, ZbbTeamInfo.class);

    List<ClubInfo> clubInfos = Lists.newArrayList();
    for (ZbbTeamInfo zbbTeamInfo : zbbTeamInfos) {
      ClubInfo clubInfo = new ClubInfo();
      clubInfo.setCnName(zbbTeamInfo.get球队());
      clubInfo.setShortName(zbbTeamInfo.get球队());
      clubInfo.setZbbTeamId(zbbTeamInfo.getTeamId());
      clubInfo.setZbbLogoUrl(zbbTeamInfo.get球队图标());
      clubInfo.setCreateTime(new Date());
      clubInfo.setOperateTime(new Date());
      clubInfos.add(clubInfo);
    }
    // clubInfoMapper.insertBatch(clubInfos);
    return "插入成功";
  }


  public String initPlayInfo(Long gameKindId, Long sessionId) {
    String playInfoStr = getPlayInfo();
    ZbbMatchInfoData zbbMatchInfoData = JSONObject.parseObject(playInfoStr, ZbbMatchInfoData.class);
    if (zbbMatchInfoData.getStatus() != 1) {
      return "数据请求status不为1";
    }
    List<ZbbMatchInfo> data = zbbMatchInfoData.getData();

    List<GamePlayInfo> playInfos = Lists.newArrayList();
    for (ZbbMatchInfo match : data) {
      GamePlayInfo gamePlayInfo = new GamePlayInfo();
      gamePlayInfo.setGameKindId(gameKindId);
      gamePlayInfo.setSeasonId(sessionId);
      gamePlayInfo.setPlaySort(match.getRound());
      gamePlayInfo.setPlayName("");
      gamePlayInfo.setIsHome(1);
      gamePlayInfo.setHomeName(match.getHteam_name());
      gamePlayInfo.setGuestName(match.getGteam_name());
      EnumPlayStatus status = match.getState().equals("3") ? EnumPlayStatus.FINISHED :
              match.getState().equals("1") ? EnumPlayStatus.NO_START : EnumPlayStatus.DOING;
      gamePlayInfo.setPlayStatus(status.getStatus());
      gamePlayInfo.setHomeScore(match.getHscore());
      gamePlayInfo.setGuestScore(match.getGscore());
      gamePlayInfo.setHasAdd(false);
      gamePlayInfo.setAddHomeScore(-1);
      gamePlayInfo.setAddGuestScore(-1);
      gamePlayInfo.setPlayTime(match.getGames_time());
      gamePlayInfo.setCreateTime(new Date());
      gamePlayInfo.setOperateTime(new Date());
      gamePlayInfo.setZbbMatchId(match.getSaishi_id());
      gamePlayInfo.setZbbHomeId(match.getHteam_id());
      gamePlayInfo.setZbbGuestId(match.getGteam_id());
      gamePlayInfo.setHomeId(-1L);
      gamePlayInfo.setGuestId(-1L);
      gamePlayInfo.setPlayCity("");
      gamePlayInfo.setPlaySite("");
      gamePlayInfo.setCocheName("");
      gamePlayInfo.setFansCount(0);
      gamePlayInfo.setMainRefer("");
      gamePlayInfo.setHasVar(true);
      gamePlayInfo.setVarRefer("");
      playInfos.add(gamePlayInfo);
    }
    for (GamePlayInfo playInfo : playInfos) {
      GamePlayInfo byZbbId = gamePlayInfoMapper.findByZbbId(playInfo.getZbbMatchId());
      if (byZbbId == null) {
        gamePlayInfoMapper.insert(playInfo);
      } else {
        if (EnumPlayStatus.NO_START.getStatus() == byZbbId.getPlayStatus()) {
          playInfo.setId(byZbbId.getId());
          playInfo.setGameKindId(byZbbId.getGameKindId());
          playInfo.setSeasonId(byZbbId.getSeasonId());
          gamePlayInfoMapper.update(playInfo);
        }
      }
    }
    return "插入成功";
  }

  public String initGamePlayerInfo(Long playInfoId) {
    GamePlayInfo gamePlayInfo = gamePlayInfoMapper.selectWithId(playInfoId);
    if (gamePlayInfo == null) {
      return "没有找到数据";
    }
    String gamePlayerInfo = getGamePlayerInfo(gamePlayInfo.getPlayTime(), String.valueOf(gamePlayInfo.getZbbMatchId()));
    ZbbMatchPlayerVo zbbMatchInfoData = JSONObject.parseObject(gamePlayerInfo, ZbbMatchPlayerVo.class);
    if (zbbMatchInfoData.getData() == null || CollectionUtils.isEmpty(zbbMatchInfoData.getData().getRongchengData())) {
      return "数据请求返回错误";
    }
    ZbbMatchPlayerVo.TeamPlayer data = zbbMatchInfoData.getData();
    List<ZbbMatchPlayer> rongchengData = data.getRongchengData();

    List<GamePlayPlayer> list = Lists.newArrayList();
    for (ZbbMatchPlayer rongchengDatum : rongchengData) {
      if (StringUtils.hasLength(rongchengDatum.getDown_time())) {
        rongchengDatum.setDown_time(rongchengDatum.getDown_time().replace("'", ""));
      }
      if (StringUtils.hasLength(rongchengDatum.getUp_time())) {
        rongchengDatum.setUp_time(rongchengDatum.getUp_time().replace("'", ""));
      }

      GamePlayPlayer gamePlayPlayer = new GamePlayPlayer();
      gamePlayPlayer.setZbbPlayerId(rongchengDatum.getPlayer_id());
      gamePlayPlayer.setPlayId(gamePlayInfo.getId());
      gamePlayPlayer.setPlayerName(rongchengDatum.getPlayer_name_cn());
      gamePlayPlayer.setIsSubGo(0);
      if (rongchengDatum.getStatus().equalsIgnoreCase("z")) {
        gamePlayPlayer.setIsFirst(1);
        gamePlayPlayer.setInMins(0);
        if (StringUtils.hasLength(rongchengDatum.getDown_time())) {
          gamePlayPlayer.setOutMins(Integer.parseInt(rongchengDatum.getDown_time()));
        } else {
          gamePlayPlayer.setOutMins(90);
        }
      } else {
        gamePlayPlayer.setIsFirst(0);
        if (StringUtils.hasLength(rongchengDatum.getUp_time())) {
          gamePlayPlayer.setIsSubGo(1);
          gamePlayPlayer.setInMins(Integer.parseInt(rongchengDatum.getUp_time()));

          if (StringUtils.hasLength(rongchengDatum.getDown_time())) {
            gamePlayPlayer.setOutMins(Integer.parseInt(rongchengDatum.getDown_time()));
          } else {
            gamePlayPlayer.setOutMins(90);
          }
        } else {
          gamePlayPlayer.setInMins(0);
          gamePlayPlayer.setOutMins(0);
        }
      }
      gamePlayPlayer.setTotalMins(gamePlayPlayer.getOutMins() - gamePlayPlayer.getInMins());
      gamePlayPlayer.setGetGoalCount(rongchengDatum.getGoal());
      gamePlayPlayer.setRedCardCount(rongchengDatum.getCard().getRed());
      gamePlayPlayer.setYellowCardCount(rongchengDatum.getCard().getYellow());
      gamePlayPlayer.setPosition(rongchengDatum.getPosition_long_cn());
      gamePlayPlayer.setCreateTime(new Date());
      gamePlayPlayer.setOperateTime(new Date());
      list.add(gamePlayPlayer);
    }
    gamePlayPlayerMapper.insertBatch(list);
    return "插入成功";
  }

  public String initPlayEvent(Long playInfoId) {
    GamePlayInfo gamePlayInfo = gamePlayInfoMapper.selectWithId(playInfoId);
    if (gamePlayInfo == null) {
      return "没有找到数据";
    }

    String playEvent = getPlayEvent(gamePlayInfo.getPlayTime(), String.valueOf(gamePlayInfo.getZbbMatchId()));
    ZbbMatchEventVo zbbMatchInfoData = JSONObject.parseObject(playEvent, ZbbMatchEventVo.class);

    List<ZbbMatchEvent> data = zbbMatchInfoData.getData();
    if (data == null) {
      return "数据为null";
    }
    List<ZbbMatchEvent> rongchengEvent = data.stream().filter(v -> v.getSl_team_id().equalsIgnoreCase("6865")).collect(Collectors.toList());

    List<GamePlayEvent> playEvents = Lists.newArrayList();
    for (ZbbMatchEvent event : rongchengEvent) {
      EnumEventType eventType = getEventType(event.getEvent_code());
      if (eventType == null) {
        continue;
      }
      GamePlayEvent gameEvent = new GamePlayEvent();
      gameEvent.setPlayId(playInfoId);
      gameEvent.setZbbPlayerId(event.getPlayer_id());
      gameEvent.setPlayerName(event.getPlayer_name_cn());
      if (event.getTime().contains("+")) {
        String add = event.getTime().replace("+", "ADD");
        String[] split1 = add.split("ADD");
        gameEvent.setEventMins(Integer.valueOf(split1[0]));
        gameEvent.setAddMins(Integer.valueOf(split1[1]));
      } else {
        gameEvent.setEventMins(Integer.valueOf(event.getTime()));
        gameEvent.setAddMins(0);
      }
      gameEvent.setCreateTime(new Date());
      gameEvent.setOperateTime(new Date());
      gameEvent.setEventType(eventType.getStatus());
      gameEvent.setEventName(eventType.getDesc());
      playEvents.add(gameEvent);
    }
    gamePlatEventMapper.insertBatch(playEvents);
    return "插入成功";
  }

  private EnumEventType getEventType(String zzbEventCode) {
    if ("1".equalsIgnoreCase(zzbEventCode)) {
      return EnumEventType.GOAL;
    }
    if ("2".equalsIgnoreCase(zzbEventCode)) {
      return EnumEventType.WULONG_GOAL;
    }
    if ("3".equalsIgnoreCase(zzbEventCode)) {
      return EnumEventType.YELLOW_CARD;
    }
    if ("4".equalsIgnoreCase(zzbEventCode)) {
      return EnumEventType.TWO_YELLOW_CARD;
    }
    if ("5".equalsIgnoreCase(zzbEventCode)) {
      return EnumEventType.RED_CARD;
    }
    if ("6".equalsIgnoreCase(zzbEventCode)) {
      return EnumEventType.POINT_GOAL;
    }
    if ("diy_20".equalsIgnoreCase(zzbEventCode)) {
      return EnumEventType.ASSIST;
    }
    if ("14".equalsIgnoreCase(zzbEventCode)) {
      return EnumEventType.EXCHANGE_DOWN;
    }
    if ("15".equalsIgnoreCase(zzbEventCode)) {
      return EnumEventType.EXCHANGE_UP;
    }
    if ("16".equalsIgnoreCase(zzbEventCode)) {
      return EnumEventType.HURT_DOWN;
    }
    if ("34".equalsIgnoreCase(zzbEventCode)) {
      return EnumEventType.MAKE_POINT_GOAL;
    }
    if ("36".equalsIgnoreCase(zzbEventCode)) {
      return EnumEventType.POINT_GOAL_LOSE;
    }
    return null;
  }

  private String getPlayEvent(Date gameDate, String zbbMatchId) {
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    String format1 = format.format(gameDate);
    String url = "https://dc4pc.qiumibao.com/dc/matchs/data/" + format1 + "/match_event_" + zbbMatchId + ".htm";
    Request request = new Request.Builder().url(url).build();
    try (Response response = okHttpClient.newCall(request).execute()) {
      if (!response.isSuccessful()) {
        throw new IOException("Unexpected code " + response);
      }
      return Objects.requireNonNull(response.body()).string();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private String getGamePlayerInfo(Date gameDate, String zbbmatchId) {
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    String format1 = format.format(gameDate);
    String url = "https://dc4pc.qiumibao.com/dc/matchs/data/" + format1 + "/match_lineup_" + zbbmatchId + ".htm";

    Request request = new Request.Builder().url(url).build();
    try (Response response = okHttpClient.newCall(request).execute()) {
      if (!response.isSuccessful()) {
        throw new IOException("Unexpected code " + response);
      }
      return Objects.requireNonNull(response.body()).string();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }


  private String getPlayInfo() {
    String url = "https://db.qiumibao.com/f/index/teamschedules?id=6865";
    Request request = new Request.Builder().url(url).build();
    try (Response response = okHttpClient.newCall(request).execute()) {
      if (!response.isSuccessful()) {
        throw new IOException("Unexpected code " + response);
      }
      return Objects.requireNonNull(response.body()).string();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private String getClubInfo() {
    return "[{\"排名\":\"1\",\"teamId\":\"2981\",\"球队\":\"上海海港\",\"场次\":\"24\",\"胜\":\"16\",\"平\":\"5\",\"负\":\"3\",\"进/失球\":\"52/23\",\"净胜球\":\"29\",\"积分\":\"53\",\"球队图标\":\"https://duihui.duoduocdn.com/zuqiu/zq_shanghaihaigangzuqiujulebu_145737.png\",\"honor\":\"\",\"lock_champions_league\":\"\",\"胜/平/负\":\"16/5/3\",\"字体颜色\":\"#e62e2e\",\"夜间字体颜色\":\"#af2d2d\",\"背景颜色\":\"#ffffff\",\"夜间背景颜色\":\"#2c2c2c\",\"标签\":\"亚冠区\",\"标签背景颜色\":\"#EF7A7A\",\"标签夜间背景颜色\":\"#A42D2D\",\"news_url\":\"\",\"球队名称\":\"上海海港\"},{\"排名\":\"2\",\"teamId\":\"2987\",\"球队\":\"山东泰山\",\"场次\":\"24\",\"胜\":\"12\",\"平\":\"8\",\"负\":\"4\",\"进/失球\":\"44/20\",\"净胜球\":\"24\",\"积分\":\"44\",\"球队图标\":\"https://duihui.duoduocdn.com/zuqiu/zq_shandongtaishan_949801.png\",\"honor\":\"\",\"lock_champions_league\":\"\",\"胜/平/负\":\"12/8/4\",\"字体颜色\":\"#34a9dc\",\"夜间字体颜色\":\"#306b84\",\"背景颜色\":\"#ffffff\",\"夜间背景颜色\":\"#2c2c2c\",\"标签\":\"亚冠附加赛区\",\"标签背景颜色\":\"#F39C9C\",\"标签夜间背景颜色\":\"#594747\",\"news_url\":\"\",\"球队名称\":\"山东泰山\"},{\"排名\":\"3\",\"teamId\":\"2980\",\"球队\":\"上海申花\",\"场次\":\"24\",\"胜\":\"12\",\"平\":\"6\",\"负\":\"6\",\"进/失球\":\"28/25\",\"净胜球\":\"3\",\"积分\":\"42\",\"球队图标\":\"https://duihui.duoduocdn.com/zuqiu/zq_shanghaishenhuazuqiujulebu_593296.png\",\"honor\":\"\",\"lock_champions_league\":\"\",\"胜/平/负\":\"12/6/6\",\"字体颜色\":\"#34a9dc\",\"夜间字体颜色\":\"#306b84\",\"背景颜色\":\"#ffffff\",\"夜间背景颜色\":\"#2c2c2c\",\"news_url\":\"\",\"球队名称\":\"上海申花\"},{\"排名\":\"4\",\"teamId\":\"2989\",\"球队\":\"北京国安\",\"场次\":\"24\",\"胜\":\"11\",\"平\":\"8\",\"负\":\"5\",\"进/失球\":\"43/29\",\"净胜球\":\"14\",\"积分\":\"41\",\"球队图标\":\"https://duihui.duoduocdn.com/zuqiu/zq_beijingguoan_571596.png\",\"honor\":\"\",\"lock_champions_league\":\"\",\"胜/平/负\":\"11/8/5\",\"字体颜色\":\"#000000\",\"夜间字体颜色\":\"#9b9b9b\",\"背景颜色\":\"#ffffff\",\"夜间背景颜色\":\"#2c2c2c\",\"news_url\":\"\",\"球队名称\":\"北京国安\"},{\"排名\":\"5\",\"teamId\":\"2910\",\"球队\":\"浙江\",\"场次\":\"24\",\"胜\":\"11\",\"平\":\"6\",\"负\":\"7\",\"进/失球\":\"39/31\",\"净胜球\":\"8\",\"积分\":\"39\",\"球队图标\":\"https://duihui.duoduocdn.com/zuqiu/zq_zhejiangzhiyezuqiujulebu_792559.png\",\"honor\":\"\",\"lock_champions_league\":\"\",\"胜/平/负\":\"11/6/7\",\"字体颜色\":\"#000000\",\"夜间字体颜色\":\"#9b9b9b\",\"背景颜色\":\"#ffffff\",\"夜间背景颜色\":\"#2c2c2c\",\"news_url\":\"\",\"球队名称\":\"浙江\"},{\"排名\":\"6\",\"teamId\":\"6866\",\"球队\":\"武汉三镇\",\"场次\":\"24\",\"胜\":\"10\",\"平\":\"9\",\"负\":\"5\",\"进/失球\":\"40/28\",\"净胜球\":\"12\",\"积分\":\"39\",\"球队图标\":\"https://duihui.duoduocdn.com/zuqiu/zq_wuhansanzhen_709254.png\",\"honor\":\"\",\"lock_champions_league\":\"\",\"胜/平/负\":\"10/9/5\",\"字体颜色\":\"#000000\",\"夜间字体颜色\":\"#9b9b9b\",\"背景颜色\":\"#ffffff\",\"夜间背景颜色\":\"#2c2c2c\",\"news_url\":\"\",\"球队名称\":\"武汉三镇\"},{\"排名\":\"7\",\"teamId\":\"6865\",\"球队\":\"成都蓉城\",\"场次\":\"24\",\"胜\":\"10\",\"平\":\"7\",\"负\":\"7\",\"进/失球\":\"35/27\",\"净胜球\":\"8\",\"积分\":\"37\",\"球队图标\":\"https://duihui.duoduocdn.com/zuqiu/zq_chengdurongchengzuqiujulebu_142633.png\",\"honor\":\"\",\"lock_champions_league\":\"\",\"胜/平/负\":\"10/7/7\",\"字体颜色\":\"#000000\",\"夜间字体颜色\":\"#9b9b9b\",\"背景颜色\":\"#ffffff\",\"夜间背景颜色\":\"#2c2c2c\",\"news_url\":\"\",\"球队名称\":\"成都蓉城\"},{\"排名\":\"8\",\"teamId\":\"2988\",\"球队\":\"天津津门虎\",\"场次\":\"24\",\"胜\":\"7\",\"平\":\"13\",\"负\":\"4\",\"进/失球\":\"29/24\",\"净胜球\":\"5\",\"积分\":\"34\",\"球队图标\":\"https://duihui.duoduocdn.com/zuqiu/zq_tianjinjinmenhuzuqiujulebu_287378.png\",\"honor\":\"\",\"lock_champions_league\":\"\",\"胜/平/负\":\"7/13/4\",\"字体颜色\":\"#000000\",\"夜间字体颜色\":\"#9b9b9b\",\"背景颜色\":\"#ffffff\",\"夜间背景颜色\":\"#2c2c2c\",\"news_url\":\"\",\"球队名称\":\"天津津门虎\"},{\"排名\":\"9\",\"teamId\":\"2992\",\"球队\":\"长春亚泰\",\"场次\":\"24\",\"胜\":\"9\",\"平\":\"7\",\"负\":\"8\",\"进/失球\":\"38/37\",\"净胜球\":\"1\",\"积分\":\"34\",\"球队图标\":\"https://duihui.duoduocdn.com/zuqiu/zq_changchunyatai_203103.png\",\"honor\":\"\",\"lock_champions_league\":\"\",\"胜/平/负\":\"9/7/8\",\"字体颜色\":\"#000000\",\"夜间字体颜色\":\"#9b9b9b\",\"背景颜色\":\"#ffffff\",\"夜间背景颜色\":\"#2c2c2c\",\"news_url\":\"\",\"球队名称\":\"长春亚泰\"},{\"排名\":\"10\",\"teamId\":\"2917\",\"球队\":\"沧州雄狮\",\"场次\":\"24\",\"胜\":\"8\",\"平\":\"6\",\"负\":\"10\",\"进/失球\":\"28/43\",\"净胜球\":\"-15\",\"积分\":\"30\",\"球队图标\":\"https://duihui.duoduocdn.com/zuqiu/zq_cangzhouxiongshi_331162.png\",\"honor\":\"\",\"lock_champions_league\":\"\",\"胜/平/负\":\"8/6/10\",\"字体颜色\":\"#000000\",\"夜间字体颜色\":\"#9b9b9b\",\"背景颜色\":\"#ffffff\",\"夜间背景颜色\":\"#2c2c2c\",\"news_url\":\"\",\"球队名称\":\"沧州雄狮\"},{\"排名\":\"11\",\"teamId\":\"2993\",\"球队\":\"河南\",\"场次\":\"24\",\"胜\":\"7\",\"平\":\"7\",\"负\":\"10\",\"进/失球\":\"27/31\",\"净胜球\":\"-4\",\"积分\":\"28\",\"球队图标\":\"https://duihui.duoduocdn.com/zuqiu/zq_henanzuqiujulebu_913793.png\",\"honor\":\"\",\"lock_champions_league\":\"\",\"胜/平/负\":\"7/7/10\",\"字体颜色\":\"#000000\",\"夜间字体颜色\":\"#9b9b9b\",\"背景颜色\":\"#ffffff\",\"夜间背景颜色\":\"#2c2c2c\",\"news_url\":\"\",\"球队名称\":\"河南\"},{\"排名\":\"12\",\"teamId\":\"2923\",\"球队\":\"梅州客家\",\"场次\":\"24\",\"胜\":\"7\",\"平\":\"5\",\"负\":\"12\",\"进/失球\":\"35/45\",\"净胜球\":\"-10\",\"积分\":\"26\",\"球队图标\":\"https://duihui.duoduocdn.com/zuqiu/zq_meizhoukejia_478203.png\",\"honor\":\"\",\"lock_champions_league\":\"\",\"胜/平/负\":\"7/5/12\",\"字体颜色\":\"#000000\",\"夜间字体颜色\":\"#9b9b9b\",\"背景颜色\":\"#ffffff\",\"夜间背景颜色\":\"#2c2c2c\",\"news_url\":\"\",\"球队名称\":\"梅州客家\"},{\"排名\":\"13\",\"teamId\":\"3643\",\"球队\":\"青岛海牛\",\"场次\":\"24\",\"胜\":\"6\",\"平\":\"5\",\"负\":\"13\",\"进/失球\":\"26/33\",\"净胜球\":\"-7\",\"积分\":\"23\",\"球队图标\":\"https://duihui.duoduocdn.com/zuqiu/zq_qingdaohainiuzuqiujulebu_952035.png\",\"honor\":\"\",\"lock_champions_league\":\"\",\"胜/平/负\":\"6/5/13\",\"字体颜色\":\"#000000\",\"夜间字体颜色\":\"#9b9b9b\",\"背景颜色\":\"#ffffff\",\"夜间背景颜色\":\"#2c2c2c\",\"news_url\":\"\",\"球队名称\":\"青岛海牛\"},{\"排名\":\"14\",\"teamId\":\"3026\",\"球队\":\"南通支云\",\"场次\":\"24\",\"胜\":\"3\",\"平\":\"10\",\"负\":\"11\",\"进/失球\":\"20/33\",\"净胜球\":\"-13\",\"积分\":\"19\",\"球队图标\":\"https://duihui.duoduocdn.com/zuqiu/zq_nantongzhiyun_157079.png\",\"honor\":\"\",\"lock_champions_league\":\"\",\"胜/平/负\":\"3/10/11\",\"字体颜色\":\"#000000\",\"夜间字体颜色\":\"#9b9b9b\",\"背景颜色\":\"#ffffff\",\"夜间背景颜色\":\"#2c2c2c\",\"news_url\":\"\",\"球队名称\":\"南通支云\"},{\"排名\":\"15\",\"teamId\":\"2916\",\"球队\":\"大连人\",\"场次\":\"24\",\"胜\":\"3\",\"平\":\"9\",\"负\":\"12\",\"进/失球\":\"20/36\",\"净胜球\":\"-16\",\"积分\":\"18\",\"球队图标\":\"https://duihui.duoduocdn.com/zuqiu/zq_dalianren_870667.png\",\"honor\":\"\",\"lock_champions_league\":\"\",\"胜/平/负\":\"3/9/12\",\"字体颜色\":\"#000000\",\"夜间字体颜色\":\"#9b9b9b\",\"背景颜色\":\"#ffffff\",\"夜间背景颜色\":\"#2c2c2c\",\"标签\":\"降级区\",\"标签背景颜色\":\"#CCCCCC\",\"标签夜间背景颜色\":\"#545962\",\"news_url\":\"\",\"球队名称\":\"大连人\"},{\"排名\":\"16\",\"teamId\":\"2912\",\"球队\":\"深圳\",\"场次\":\"24\",\"胜\":\"3\",\"平\":\"3\",\"负\":\"18\",\"进/失球\":\"21/60\",\"净胜球\":\"-39\",\"积分\":\"12\",\"球队图标\":\"https://duihui.duoduocdn.com/zuqiu/zq_shenzhenshizuqiujulebu_432006.png\",\"honor\":\"\",\"lock_champions_league\":\"\",\"胜/平/负\":\"3/3/18\",\"字体颜色\":\"#000000\",\"夜间字体颜色\":\"#9b9b9b\",\"背景颜色\":\"#ffffff\",\"夜间背景颜色\":\"#2c2c2c\",\"news_url\":\"\",\"球队名称\":\"深圳\"}]";
  }

  private String getZbbPalyerInfo() {
    String url = "https://db.qiumibao.com/f/index/team?id=6865";
    Request request = new Request.Builder().url(url).build();
    try (Response response = okHttpClient.newCall(request).execute()) {
      if (!response.isSuccessful()) {
        throw new IOException("Unexpected code " + response);
      }
      return Objects.requireNonNull(response.body()).string();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
