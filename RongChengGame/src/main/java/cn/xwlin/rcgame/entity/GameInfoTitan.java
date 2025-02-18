package cn.xwlin.rcgame.entity;

public class GameInfoTitan {
    private Integer id;

    private Integer gameId;

    private String titanGameId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public String getTitanGameId() {
        return titanGameId;
    }

    public void setTitanGameId(String titanGameId) {
        this.titanGameId = titanGameId == null ? null : titanGameId.trim();
    }
}