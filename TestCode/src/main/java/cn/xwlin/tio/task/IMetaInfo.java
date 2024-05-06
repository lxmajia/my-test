package cn.xwlin.tio.task;

public interface IMetaInfo {
    /**
     * 上报任务执行参数/状态等记录信息
     *
     * @param paramName  参数名
     * @param paramValue 参数值
     */
    void addCustomStatus(String paramName, String paramValue);

    /**
     * 执行成功
     */
    void executeSucceed();

    /**
     * 执行成功上报结果
     *
     * @param desc 说明
     */
    void executeSucceed(String desc);

    /**
     * 执行失败
     */
    void executeFailed();

    /**
     * 执行失败上报结果
     *
     * @param desc 说明
     */
    void executeFailed(String desc);
}