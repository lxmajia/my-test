package cn.xwlin.rcgame.entity;

public class GameSeason {
    private Integer id;

    private Integer gameTypeId;

    private String seasonName;

    private String seasonShortName;

    private String seasonLogo;

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

    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName == null ? null : seasonName.trim();
    }

    public String getSeasonShortName() {
        return seasonShortName;
    }

    public void setSeasonShortName(String seasonShortName) {
        this.seasonShortName = seasonShortName == null ? null : seasonShortName.trim();
    }

    public String getSeasonLogo() {
        return seasonLogo;
    }

    public void setSeasonLogo(String seasonLogo) {
        this.seasonLogo = seasonLogo == null ? null : seasonLogo.trim();
    }
}