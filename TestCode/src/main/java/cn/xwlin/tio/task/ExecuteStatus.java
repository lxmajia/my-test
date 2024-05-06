package cn.xwlin.tio.task;

import lombok.Getter;
import lombok.Setter;

/**
 * @author MaJiaXueYuan.lx
 * @ClassName ExecuteStatus.java
 * @createTime 2022-12-8-0008
 */
@Getter
@Setter
public class ExecuteStatus {
    private ExecuteStatusType statusType;
    private String key;
    private String value;
    private Long time;

    private ExecuteStatus(ExecuteStatusType statusType, String key, String value, Long time) {
        this.statusType = statusType;
        this.key = key;
        this.value = value;
        this.time = time;
    }

    public static ExecuteStatus build(ExecuteStatusType statusType, String key, String value) {
        return new ExecuteStatus(statusType, key, value, System.currentTimeMillis());
    }
}
