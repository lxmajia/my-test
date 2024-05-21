package cn.xwlin.configBeanFactory;

import redis.clients.jedis.JedisPoolConfig;

/**
 * @author yao.wang2 2023/9/18
 */
public class HotSwitchJedisConfig {

  private JedisPoolConfig jedisPoolConfig;

  private String host;

  private int port;

  private String password;

  private int timeOut;

  public JedisPoolConfig getJedisPoolConfig() {
    return jedisPoolConfig;
  }

  public void setJedisPoolConfig(JedisPoolConfig jedisPoolConfig) {
    this.jedisPoolConfig = jedisPoolConfig;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getTimeOut() {
    return timeOut;
  }

  public void setTimeOut(int timeOut) {
    this.timeOut = timeOut;
  }
}
