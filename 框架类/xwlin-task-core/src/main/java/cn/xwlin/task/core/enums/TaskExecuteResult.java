package cn.xwlin.task.core.enums;

import lombok.Getter;

@Getter
public enum TaskExecuteResult {
    /**
     * 任务状态
     */
    SUCCESS("SUCCESS", "成功"),
    FAILED("FAILED", "失败"),

    ;


    private String msgType;
    private String desc;

    TaskExecuteResult(String msgType, String desc) {
        this.msgType = msgType;
        this.desc = desc;
    }
}
