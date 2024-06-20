package cn.xwlin.configcenter.vo;

/**
 * @author xiang.liao
 * @create 2024/6/20
 */
public class LoginResp {
  private String nickName;
  private String token;

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
