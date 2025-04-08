package cn.xwlin.config.factionbean.myredis;

/**
 * @author xiang.liao
 * @create 2025/3/28
 */
public class MyRedisConfig {
  private String redisUrl;
  private Integer redisPort;
  private String username;
  private String password;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRedisUrl() {
    return redisUrl;
  }

  public void setRedisUrl(String redisUrl) {
    this.redisUrl = redisUrl;
  }

  public Integer getRedisPort() {
    return redisPort;
  }

  public void setRedisPort(Integer redisPort) {
    this.redisPort = redisPort;
  }
}
