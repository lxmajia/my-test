package cn.xwlin.rcgame.controller.response;

/**
 * @author xiang.liao
 * @create 2025/2/21
 */
public class ToDayGame {
  private Integer gameId;
  private String gameName;
  private String gameTime;

  public Integer getGameId() {
    return gameId;
  }

  public void setGameId(Integer gameId) {
    this.gameId = gameId;
  }

  public String getGameName() {
    return gameName;
  }

  public void setGameName(String gameName) {
    this.gameName = gameName;
  }

  public String getGameTime() {
    return gameTime;
  }

  public void setGameTime(String gameTime) {
    this.gameTime = gameTime;
  }
}
