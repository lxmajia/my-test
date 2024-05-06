package cn.xwlin.tio.task;

import lombok.Getter;
import lombok.Setter;

/**
 * @author MaJiaXueYuan.lx
 * @ClassName ExecutingTaskInfo.java
 * @createTime 2022-12-8-0008
 */
@Getter
@Setter
public class ExecutingTaskInfo {
    TaskService taskService;
    TaskMetaInfo taskMetaContext;
}
