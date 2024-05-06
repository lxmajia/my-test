package cn.xwlin.protocol;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xiang.liao
 * @create 2024/3/5
 */
@Getter
@Setter
public class TextMsg extends Msg {
  private Long fromUserId;
  private Long toUserId;
  private String msg;

  public TextMsg() {
    this.setMsgType(MsgTypeCons.TEXT);
  }
}
