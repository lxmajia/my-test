package cn.xwlin.web.vo;

/**
 * @author xiang.liao
 * @create 2024/5/14
 */
public class LoginInfo {
  private Long userId;
  private String token;

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
