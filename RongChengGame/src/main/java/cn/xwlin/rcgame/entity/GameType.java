package cn.xwlin.rcgame.entity;

public class GameType {
    private Integer id;

    private String gameType;

    private String gameName;

    private String gameShortName;

    private String gameLogo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType == null ? null : gameType.trim();
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName == null ? null : gameName.trim();
    }

    public String getGameShortName() {
        return gameShortName;
    }

    public void setGameShortName(String gameShortName) {
        this.gameShortName = gameShortName == null ? null : gameShortName.trim();
    }

    public String getGameLogo() {
        return gameLogo;
    }

    public void setGameLogo(String gameLogo) {
        this.gameLogo = gameLogo == null ? null : gameLogo.trim();
    }
}