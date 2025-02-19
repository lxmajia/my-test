package cn.xwlin.rcgame.entity;

import java.util.Date;
import java.util.List;

public class GameInfoPlayer {
  private Integer id;

  private Integer seasonId;

  private Integer gameId;

  private Integer clubId;

  private Integer seasonPlayerId;

  private Integer seasonWearNum;

  private Date birthday;

  private Integer height;

  private String national;

  private Date regiesrTime;

  private Integer startPlayer;

  private Integer positionId;

  private Integer upMinute;

  private Integer offMinute;

  private Integer gameMinute;

  private Integer shotCount;

  private Integer shotTargetCount;

  private Integer passSuccessRate;


  private List<GameInfoEvent> eventList;
  private String name;
  private String detailUrl;

  public String getDetailUrl() {
    return detailUrl;
  }

  public void setDetailUrl(String detailUrl) {
    this.detailUrl = detailUrl;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<GameInfoEvent> getEventList() {
    return eventList;
  }

  public void setEventList(List<GameInfoEvent> eventList) {
    this.eventList = eventList;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getSeasonId() {
    return seasonId;
  }

  public void setSeasonId(Integer seasonId) {
    this.seasonId = seasonId;
  }

  public Integer getGameId() {
    return gameId;
  }

  public void setGameId(Integer gameId) {
    this.gameId = gameId;
  }

  public Integer getClubId() {
    return clubId;
  }

  public void setClubId(Integer clubId) {
    this.clubId = clubId;
  }

  public Integer getSeasonPlayerId() {
    return seasonPlayerId;
  }

  public void setSeasonPlayerId(Integer seasonPlayerId) {
    this.seasonPlayerId = seasonPlayerId;
  }

  public Integer getSeasonWearNum() {
    return seasonWearNum;
  }

  public void setSeasonWearNum(Integer seasonWearNum) {
    this.seasonWearNum = seasonWearNum;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public Integer getHeight() {
    return height;
  }

  public void setHeight(Integer height) {
    this.height = height;
  }

  public String getNational() {
    return national;
  }

  public void setNational(String national) {
    this.national = national == null ? null : national.trim();
  }

  public Date getRegiesrTime() {
    return regiesrTime;
  }

  public void setRegiesrTime(Date regiesrTime) {
    this.regiesrTime = regiesrTime;
  }

  public Integer getStartPlayer() {
    return startPlayer;
  }

  public void setStartPlayer(Integer startPlayer) {
    this.startPlayer = startPlayer;
  }

  public Integer getPositionId() {
    return positionId;
  }

  public void setPositionId(Integer positionId) {
    this.positionId = positionId;
  }

  public Integer getUpMinute() {
    return upMinute;
  }

  public void setUpMinute(Integer upMinute) {
    this.upMinute = upMinute;
  }

  public Integer getOffMinute() {
    return offMinute;
  }

  public void setOffMinute(Integer offMinute) {
    this.offMinute = offMinute;
  }

  public Integer getGameMinute() {
    return gameMinute;
  }

  public void setGameMinute(Integer gameMinute) {
    this.gameMinute = gameMinute;
  }

  public Integer getShotCount() {
    return shotCount;
  }

  public void setShotCount(Integer shotCount) {
    this.shotCount = shotCount;
  }

  public Integer getShotTargetCount() {
    return shotTargetCount;
  }

  public void setShotTargetCount(Integer shotTargetCount) {
    this.shotTargetCount = shotTargetCount;
  }

  public Integer getPassSuccessRate() {
    return passSuccessRate;
  }

  public void setPassSuccessRate(Integer passSuccessRate) {
    this.passSuccessRate = passSuccessRate;
  }
}