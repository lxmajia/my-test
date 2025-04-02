package cn.xwlin.configcenter.entity;

import java.util.Date;

public class ConfigInfoHis {
    private Long id;

    private Long appModuleId;

    private String configKey;

    private String uniqueKey;

    private String configType;

    private Long operateId;

    private Date createTime;
    private String oldConfigValue;

    private String newConfigValue;

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

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey == null ? null : uniqueKey.trim();
    }

    public String getConfigType() {
        return configType;
    }

    public void setConfigType(String configType) {
        this.configType = configType == null ? null : configType.trim();
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