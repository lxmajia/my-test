package cn.xwlin.tio.task;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MaJiaXueYuan.lx
 * @ClassName TaskMeta.java
 * @createTime 2022-12-8-0008
 */
@Getter
@Setter
public class TaskMetaInfo implements IMetaInfo {
    /**
     * 当前执行类名
     */
    private String clazzName;
    /**
     * 下发时间-UTC时间
     */
    private Long issueTimeMills;
    /**
     * 任务ID
     */
    private Long taskId;
    /**
     * 本地执行ID
     */
    private Long executeId;

    private volatile List<ExecuteStatus> statusList = new ArrayList();

    @Override
    public void addCustomStatus(String paramName, String paramValue) {
        if (StringUtils.hasText(paramName)) {
            addExecuteStatus(ExecuteStatus.build(ExecuteStatusType.PARAMS, paramName, paramValue));
        }
    }

    @Override
    public void executeSucceed() {
        addExecuteStatus(ExecuteStatus.build(ExecuteStatusType.SUCCESS, "", ""));
    }

    @Override
    public void executeSucceed(String desc) {
        addExecuteStatus(ExecuteStatus.build(ExecuteStatusType.SUCCESS, "", desc));
    }

    @Override
    public void executeFailed() {
        addExecuteStatus(ExecuteStatus.build(ExecuteStatusType.FAILED, "", ""));
    }

    @Override
    public void executeFailed(String desc) {
        addExecuteStatus(ExecuteStatus.build(ExecuteStatusType.FAILED, "", desc));
    }

    private void addExecuteStatus(ExecuteStatus executeStatus) {
        synchronized (this) {
            this.statusList.add(executeStatus);
        }
    }
}
