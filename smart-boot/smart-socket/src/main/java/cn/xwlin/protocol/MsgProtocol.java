package cn.xwlin.protocol;

import org.smartboot.socket.Protocol;
import org.smartboot.socket.transport.AioSession;
import java.nio.ByteBuffer;

/**
 * @author xiang.liao
 * @create 2024/3/5
 */
public class MsgProtocol implements Protocol<Msg> {

  @Override
  public Msg decode(ByteBuffer readBuffer, AioSession session) {
    return MessageEncryptUtil.decode(readBuffer);
  }
}
