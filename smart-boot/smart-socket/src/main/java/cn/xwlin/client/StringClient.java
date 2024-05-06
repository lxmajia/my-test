package cn.xwlin.client;

import cn.xwlin.protocol.Msg;
import cn.xwlin.protocol.MsgProtocol;
import cn.xwlin.protocol.TextMsg;
import org.smartboot.socket.MessageProcessor;
import org.smartboot.socket.transport.AioQuickClient;
import org.smartboot.socket.transport.AioSession;
import org.smartboot.socket.transport.WriteBuffer;

import java.io.IOException;
import java.net.SocketOption;
import java.nio.charset.StandardCharsets;

public class StringClient {

    public static void main(String[] args) throws IOException {
        MessageProcessor<Msg> processor = (session, msg) -> System.out.println("receive from " +
                "server: " + msg);
        AioQuickClient client = new AioQuickClient("127.0.0.1", 8888, new MsgProtocol(),
                processor);
        AioSession session = client.start();

        TextMsg textMsg = new TextMsg();
        textMsg.setFromUserId(438944209L);
        textMsg.setToUserId(234348992L);
        textMsg.setMsg("今天天气真好啊，走，我们出去玩。");

        byte[] bytes = textMsg.getMsg().getBytes(StandardCharsets.UTF_8);

        WriteBuffer writeBuffer = session.writeBuffer();
        writeBuffer.writeInt(textMsg.getMsgType());
        writeBuffer.writeLong(textMsg.getFromUserId());
        writeBuffer.writeLong(textMsg.getToUserId());
        writeBuffer.writeInt(bytes.length);
        writeBuffer.write(bytes);
        writeBuffer.flush();

        System.out.println("----");
    }
}