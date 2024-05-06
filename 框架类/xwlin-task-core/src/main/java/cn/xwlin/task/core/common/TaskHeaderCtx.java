package cn.xwlin.task.core.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TaskHeaderCtx implements Serializable {
    /**
     * 任务ID
     */
    private Long taskId;
    /**
     * 执行ID
     */
    private Long executId;
    /**
     * 类名
     */
    private String clazzName;
    /**
     * 下发时间
     */
    private Long executeTime;
    /**
     * 参数
     */
    private String param;

    public void execParam(String key, String value) {
    }

    public void failed() {

    }
}
