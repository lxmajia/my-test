package cn.xwlin.rcgame.entity;

import java.util.Date;

public class GameInfo {
    private Integer id;

    private Integer gameTypeId;

    private Integer gameSeasonId;

    private String gameNameDesc;

    private Integer mainClubId;

    private String mainClubName;

    private Integer mainControlRate;

    private Integer guestClubId;

    private String guestClubName;

    private Integer matchStatus;

    private Date matchTime;

    private Date realMatchTime;

    private Integer halfMainScore;

    private Integer halfGuestScore;

    private Integer fullMainScore;

    private Integer fullGuestScore;

    private Integer mainPoint;

    private Integer guestPoint;

    private String matchCity;

    private String matchStadiumName;

    private Integer positionType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGameTypeId() {
        return gameTypeId;
    }

    public void setGameTypeId(Integer gameTypeId) {
        this.gameTypeId = gameTypeId;
    }

    public Integer getGameSeasonId() {
        return gameSeasonId;
    }

    public void setGameSeasonId(Integer gameSeasonId) {
        this.gameSeasonId = gameSeasonId;
    }

    public String getGameNameDesc() {
        return gameNameDesc;
    }

    public void setGameNameDesc(String gameNameDesc) {
        this.gameNameDesc = gameNameDesc == null ? null : gameNameDesc.trim();
    }

    public Integer getMainClubId() {
        return mainClubId;
    }

    public void setMainClubId(Integer mainClubId) {
        this.mainClubId = mainClubId;
    }

    public String getMainClubName() {
        return mainClubName;
    }

    public void setMainClubName(String mainClubName) {
        this.mainClubName = mainClubName == null ? null : mainClubName.trim();
    }

    public Integer getMainControlRate() {
        return mainControlRate;
    }

    public void setMainControlRate(Integer mainControlRate) {
        this.mainControlRate = mainControlRate;
    }

    public Integer getGuestClubId() {
        return guestClubId;
    }

    public void setGuestClubId(Integer guestClubId) {
        this.guestClubId = guestClubId;
    }

    public String getGuestClubName() {
        return guestClubName;
    }

    public void setGuestClubName(String guestClubName) {
        this.guestClubName = guestClubName == null ? null : guestClubName.trim();
    }

    public Integer getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(Integer matchStatus) {
        this.matchStatus = matchStatus;
    }

    public Date getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(Date matchTime) {
        this.matchTime = matchTime;
    }

    public Date getRealMatchTime() {
        return realMatchTime;
    }

    public void setRealMatchTime(Date realMatchTime) {
        this.realMatchTime = realMatchTime;
    }

    public Integer getHalfMainScore() {
        return halfMainScore;
    }

    public void setHalfMainScore(Integer halfMainScore) {
        this.halfMainScore = halfMainScore;
    }

    public Integer getHalfGuestScore() {
        return halfGuestScore;
    }

    public void setHalfGuestScore(Integer halfGuestScore) {
        this.halfGuestScore = halfGuestScore;
    }

    public Integer getFullMainScore() {
        return fullMainScore;
    }

    public void setFullMainScore(Integer fullMainScore) {
        this.fullMainScore = fullMainScore;
    }

    public Integer getFullGuestScore() {
        return fullGuestScore;
    }

    public void setFullGuestScore(Integer fullGuestScore) {
        this.fullGuestScore = fullGuestScore;
    }

    public Integer getMainPoint() {
        return mainPoint;
    }

    public void setMainPoint(Integer mainPoint) {
        this.mainPoint = mainPoint;
    }

    public Integer getGuestPoint() {
        return guestPoint;
    }

    public void setGuestPoint(Integer guestPoint) {
        this.guestPoint = guestPoint;
    }

    public String getMatchCity() {
        return matchCity;
    }

    public void setMatchCity(String matchCity) {
        this.matchCity = matchCity == null ? null : matchCity.trim();
    }

    public String getMatchStadiumName() {
        return matchStadiumName;
    }

    public void setMatchStadiumName(String matchStadiumName) {
        this.matchStadiumName = matchStadiumName == null ? null : matchStadiumName.trim();
    }

    public Integer getPositionType() {
        return positionType;
    }

    public void setPositionType(Integer positionType) {
        this.positionType = positionType;
    }
}