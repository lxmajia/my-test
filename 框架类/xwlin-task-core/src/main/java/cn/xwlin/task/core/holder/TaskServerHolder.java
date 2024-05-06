package cn.xwlin.task.core.holder;

import cn.xwlin.task.core.TaskServer;

public class TaskServerHolder {
    public static TaskServer taskServer = null;

    public static TaskServer getTaskServer() {
        return taskServer;
    }
}
