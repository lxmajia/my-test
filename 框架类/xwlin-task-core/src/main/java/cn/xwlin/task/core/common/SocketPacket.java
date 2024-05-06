package cn.xwlin.task.core.common;

import lombok.Getter;
import lombok.Setter;
import org.tio.core.intf.Packet;

@Getter
@Setter
public class SocketPacket extends Packet {
    private static final long serialVersionUID = -172060606924066412L;
    public static final int HEADER_LENGHT = 4;//消息头的长度
    public static final String CHARSET = "utf-8";
    private byte[] body;
}