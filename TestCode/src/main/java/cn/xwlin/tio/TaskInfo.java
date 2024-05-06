package cn.xwlin.tio;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskInfo {
    private String taskType;
    private String className;
    private String taskParam;
    private Long utcTime;
}
