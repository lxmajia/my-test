package cn.xwlin.task.core.enums;

import lombok.Getter;

@Getter
public enum C2SMsgType {
    /**
     * 客户端发给服务端的消息类型
     */
    BIND_APP("BIND_APP", "客户端连接成功-绑定到某个应用"),
    CLIENT_PING("CLIENT_PING", "客户端发起心跳"),
    CLIENT_PONG("CLIENT_PONG", "客户端回复心跳"),
    DISPATCHER_RESP("DISPATCHER_RESP", "任务下发收到-回复"),
    REPORT_STATUS("REPORT_STATUS", "客户端上报任务状态(参数)"),
    ;


    private String msgType;
    private String desc;

    C2SMsgType(String msgType, String desc) {
        this.msgType = msgType;
        this.desc = desc;
    }
}
