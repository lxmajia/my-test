package cn.xwlin.source.config;

import javax.sql.DataSource;

public class DatasourceConfig {
    private Boolean primary = false;
    private String username;
    private String password;
    private String url;
    private Class<? extends DataSource> type;
    private String driverClass;
    //初始化连接池的连接数量 大小，最小，最大
    private int initSize = 2;
    private int minIdle = 2;
    private int maxActive = 8;
    //配置获取连接等待超时的时间
    private long maxWait = -1L;
    private String validationQuery;
    private int validationQueryTimeOut = 200;

    public Boolean getPrimary() {
        return primary;
    }

    public void setPrimary(Boolean primary) {
        this.primary = primary;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Class<? extends DataSource> getType() {
        return type;
    }

    public void setType(Class<? extends DataSource> type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public int getInitSize() {
        return initSize;
    }

    public void setInitSize(int initSize) {
        this.initSize = initSize;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public long getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(long maxWait) {
        this.maxWait = maxWait;
    }

    public String getValidationQuery() {
        return validationQuery;
    }

    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
    }

    public int getValidationQueryTimeOut() {
        return validationQueryTimeOut;
    }

    public void setValidationQueryTimeOut(int validationQueryTimeOut) {
        this.validationQueryTimeOut = validationQueryTimeOut;
    }
}