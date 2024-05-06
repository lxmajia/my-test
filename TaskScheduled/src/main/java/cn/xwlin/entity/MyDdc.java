package cn.xwlin.entity;

import java.util.Date;

public class MyDdc {
    private Integer id;

    private String projectId;

    private String appId;

    private String taskName;

    private String taskDesc;

    private String cronExpress;

    private String clazzPath;

    private String params;

    private Date timeWheelEnd;

    private Integer timeoutSecond;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc == null ? null : taskDesc.trim();
    }

    public String getCronExpress() {
        return cronExpress;
    }

    public void setCronExpress(String cronExpress) {
        this.cronExpress = cronExpress == null ? null : cronExpress.trim();
    }

    public String getClazzPath() {
        return clazzPath;
    }

    public void setClazzPath(String clazzPath) {
        this.clazzPath = clazzPath == null ? null : clazzPath.trim();
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }

    public Date getTimeWheelEnd() {
        return timeWheelEnd;
    }

    public void setTimeWheelEnd(Date timeWheelEnd) {
        this.timeWheelEnd = timeWheelEnd;
    }

    public Integer getTimeoutSecond() {
        return timeoutSecond;
    }

    public void setTimeoutSecond(Integer timeoutSecond) {
        this.timeoutSecond = timeoutSecond;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}