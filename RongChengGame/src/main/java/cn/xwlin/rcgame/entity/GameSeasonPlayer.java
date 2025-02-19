package cn.xwlin.rcgame.entity;

import java.util.Date;

public class GameSeasonPlayer {
  private Integer id;

  private Integer clubId;
  private Integer seasonId;

  private String cnName;

  private String enName;

  private Integer seasonWearNum;

  private Date birthday;

  private Integer height;

  private String national;

  private Date regiesrTime;

  private String titanDetailUrl;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getClubId() {
    return clubId;
  }

  public void setClubId(Integer clubId) {
    this.clubId = clubId;
  }

  public Integer getSeasonId() {
    return seasonId;
  }

  public void setSeasonId(Integer seasonId) {
    this.seasonId = seasonId;
  }

  public String getCnName() {
    return cnName;
  }

  public void setCnName(String cnName) {
    this.cnName = cnName == null ? null : cnName.trim();
  }

  public String getEnName() {
    return enName;
  }

  public void setEnName(String enName) {
    this.enName = enName == null ? null : enName.trim();
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

  public String getTitanDetailUrl() {
    return titanDetailUrl;
  }

  public void setTitanDetailUrl(String titanDetailUrl) {
    this.titanDetailUrl = titanDetailUrl == null ? null : titanDetailUrl.trim();
  }
}