package cn.xwlin.config.factionbean.myredis;

/**
 * @author xiang.liao
 * @create 2025/3/28
 */
public class MyRedisProxy {
  private String redisUrl;
  private String redisPort;
  private String linkUrl;

  public String getLinkUrl() {
    return linkUrl;
  }

  public void setLinkUrl(String linkUrl) {
    this.linkUrl = linkUrl;
  }

  public String getRedisUrl() {
    return redisUrl;
  }

  public void setRedisUrl(String redisUrl) {
    this.redisUrl = redisUrl;
  }

  public String getRedisPort() {
    return redisPort;
  }

  public void setRedisPort(String redisPort) {
    this.redisPort = redisPort;
  }
}
