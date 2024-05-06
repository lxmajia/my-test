package cn.xwlin.tio.common;

import lombok.Getter;
import lombok.Setter;

/**
 * @author MaJiaXueYuan.lx
 * @ClassName TaskInfo.java
 * @createTime 2022-11-27-0027
 */
@Getter
@Setter
public class TaskInfo {
    private String groupName;
    private String taskType;
    private String className;
    private String taskParam;
    private Long utcTime;
}
