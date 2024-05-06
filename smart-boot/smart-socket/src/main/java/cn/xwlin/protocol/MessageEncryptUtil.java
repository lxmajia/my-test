package cn.xwlin.protocol;

import org.smartboot.socket.transport.WriteBuffer;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/**
 * @author xiang.liao
 * @create 2024/3/5
 */
public class MessageEncryptUtil {

  public static Msg decode(ByteBuffer readBuffer) {
    int remaining = readBuffer.remaining();
    if (remaining < Integer.BYTES) {
      return null;
    }
    readBuffer.mark();
    int msgType = readBuffer.getInt();
    if (msgType > readBuffer.remaining()) {
      readBuffer.reset();
      return null;
    }

    Msg msg = null;
    switch (msgType) {
      case MsgTypeCons.TEXT:
        long fromUserId = readBuffer.getLong();
        long toUserId = readBuffer.getLong();
        int msgLength = readBuffer.getInt();
        byte[] msgByte = new byte[msgLength];
        readBuffer.get(msgByte);
        String msgContent;
        try {
          msgContent = new String(msgByte, "UTF-8");
        } catch (UnsupportedEncodingException e) {
          return null;
        }
        TextMsg textMsg = new TextMsg();
        textMsg.setFromUserId(fromUserId);
        textMsg.setToUserId(toUserId);
        textMsg.setMsgType(msgType);
        textMsg.setMsg(msgContent);
        msg = textMsg;
        break;
      case MsgTypeCons.PIC:
        break;
      default:
        return null;
    }
    return msg;
  }

}
