package cn.xwlin.cons;

import lombok.Getter;

@Getter
public enum SendStatus {
    /**
     * 发送状态
     */
    INIT(0, "初始化"),
    SUCCESS(2, "初始化"),
    FAILED(99, "发送失败"),

    ;

    private Integer status;
    private String desc;

    SendStatus(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}
