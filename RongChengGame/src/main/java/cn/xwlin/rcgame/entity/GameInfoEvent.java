package cn.xwlin.rcgame.entity;

public class GameInfoEvent {
    private Integer id;
    private Integer seasonId;
    private Integer clubId;

    private Integer gameId;

    private Integer gameInfoPlayerId;

    private Integer eventType;

    private String eventDesc;

    private Integer gameMinute;

    private Integer gameMinuteAdd;

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

    public Integer getClubId() {
        return clubId;
    }

    public void setClubId(Integer clubId) {
        this.clubId = clubId;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Integer getGameInfoPlayerId() {
        return gameInfoPlayerId;
    }

    public void setGameInfoPlayerId(Integer gameInfoPlayerId) {
        this.gameInfoPlayerId = gameInfoPlayerId;
    }

    public Integer getEventType() {
        return eventType;
    }

    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc == null ? null : eventDesc.trim();
    }

    public Integer getGameMinute() {
        return gameMinute;
    }

    public void setGameMinute(Integer gameMinute) {
        this.gameMinute = gameMinute;
    }

    public Integer getGameMinuteAdd() {
        return gameMinuteAdd;
    }

    public void setGameMinuteAdd(Integer gameMinuteAdd) {
        this.gameMinuteAdd = gameMinuteAdd;
    }
}