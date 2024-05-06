package cn.xwlin.server;

import cn.xwlin.protocol.Msg;
import cn.xwlin.protocol.MsgProtocol;
import com.alibaba.fastjson2.JSON;
import org.smartboot.socket.MessageProcessor;
import org.smartboot.socket.StateMachineEnum;
import org.smartboot.socket.transport.AioQuickServer;
import org.smartboot.socket.transport.AioSession;
import org.smartboot.socket.transport.WriteBuffer;

import java.io.IOException;
import java.net.SocketOption;

public class StringServer {

  public static void main(String[] args) throws IOException {
    MessageProcessor<Msg> processor = new MessageProcessor<Msg>() {
      @Override
      public void process(AioSession session, Msg msg) {
        WriteBuffer outputStream = session.writeBuffer();
        System.out.println("receive from client: " + JSON.toJSONString(msg));
      }

      @Override
      public void stateEvent(AioSession session, StateMachineEnum stateMachineEnum, Throwable throwable) {
        System.out.println(session.getSessionID());
        System.out.println("Status:" + stateMachineEnum.name());
      }
    };
    AioQuickServer server = new AioQuickServer(8888, new MsgProtocol(), processor);
    server.start();
  }
}