package cn.xwlin.tio.task;

import lombok.Getter;
import lombok.Setter;
import org.tio.client.ClientChannelContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MaJiaXueYuan.lx
 * @ClassName TaskContext.java
 * @createTime 2022-12-9-0009
 */
public class TaskContext {
    private static String appId;
    private static Map<String, TaskService> taskMap;
    private static ClientChannelContext clientChannelContext;

    public static String getAppId() {
        return appId;
    }

    public static void setAppId(String appId) {
        TaskContext.appId = appId;
    }

    public static Map<String, TaskService> getTaskMap() {
        return taskMap;
    }

    public static void setTaskMap(Map<String, TaskService> taskMap) {
        TaskContext.taskMap = taskMap;
    }

    public static ClientChannelContext getClientChannelContext() {
        return clientChannelContext;
    }

    public static void setClientChannelContext(ClientChannelContext clientChannelContext) {
        TaskContext.clientChannelContext = clientChannelContext;
    }
}
