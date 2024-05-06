package cn.xwlin.tio.task;

/**
 * @author MaJiaXueYuan.lx
 * @ClassName TaskService.java
 * @createTime 2022-12-8-0008
 */
public interface TaskService {
    /**
     * 执行任务
     *
     * @param params      自定义参数
     * @param metaContext 任务元数据
     */
    void executeTask(String params, TaskMetaInfo metaContext);
}
