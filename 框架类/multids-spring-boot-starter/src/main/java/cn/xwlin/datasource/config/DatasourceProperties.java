package cn.xwlin.datasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.sql.DataSource;
import java.util.Map;

@EnableConfigurationProperties
@ConfigurationProperties(prefix = "dynamic.datasource")
public class DatasourceProperties {

  private Class<? extends DataSource> type = DruidDataSource.class;

  private String driverClass = "com.mysql.cj.jdbc.Driver";

  private Map<String, DatasourceConfig> config;

  public Class<? extends DataSource> getType() {
    return type;
  }

  public void setType(Class<? extends DataSource> type) {
    this.type = type;
  }

  public String getDriverClass() {
    return driverClass;
  }

  public void setDriverClass(String driverClass) {
    this.driverClass = driverClass;
  }

  public Map<String, DatasourceConfig> getConfig() {
    return config;
  }

  public void setConfig(Map<String, DatasourceConfig> config) {
    this.config = config;
  }
}