package cn.xwlin.configcenter.entity;

import java.util.Date;

public class SysConfigHis {
    private Long id;

    private Long appModuleId;

    private String configKey;

    private String oldConfigValue;

    private String newConfigValue;

    private Long operateId;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAppModuleId() {
        return appModuleId;
    }

    public void setAppModuleId(Long appModuleId) {
        this.appModuleId = appModuleId;
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey == null ? null : configKey.trim();
    }

    public String getOldConfigValue() {
        return oldConfigValue;
    }

    public void setOldConfigValue(String oldConfigValue) {
        this.oldConfigValue = oldConfigValue == null ? null : oldConfigValue.trim();
    }

    public String getNewConfigValue() {
        return newConfigValue;
    }

    public void setNewConfigValue(String newConfigValue) {
        this.newConfigValue = newConfigValue == null ? null : newConfigValue.trim();
    }

    public Long getOperateId() {
        return operateId;
    }

    public void setOperateId(Long operateId) {
        this.operateId = operateId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}