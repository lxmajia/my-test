package cn.xwlin.protocol;

/**
 * @author xiang.liao
 * @create 2024/3/5
 */
public interface MsgTypeCons {
  int TEXT = 1;
  int PIC = 2;
  int VIDEO = 3;
  int VOICE = 4;
  int RED_PACKAGE = 5;

  int PING = 999;
  int PONG = 9999;
}
