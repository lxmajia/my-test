package cn.xwlin.task.core.util;

public class GroupIdUtil {
    public static String getGroupId(String projectId, String appId) {
        return projectId + "=" + appId;
    }
}
