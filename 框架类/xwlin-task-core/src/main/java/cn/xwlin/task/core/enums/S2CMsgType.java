package cn.xwlin.task.core.enums;

import lombok.Getter;

@Getter
public enum S2CMsgType {
    /**
     * 服务端发给客户端的消息类型
     */
    SERVER_PING("SERVER_PING", "服务端发起心跳"),
    SERVER_PONG("SERVER_PONG", "服务端回复心跳"),
    DISPATCHER_TASK("DISPATCHER_TASK", "下发任务"),

    ;


    private String msgType;
    private String desc;

    S2CMsgType(String msgType, String desc) {
        this.msgType = msgType;
        this.desc = desc;
    }
}
