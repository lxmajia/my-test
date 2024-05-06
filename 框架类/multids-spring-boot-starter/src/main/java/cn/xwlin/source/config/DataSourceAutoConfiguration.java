package cn.xwlin.source.config;

import cn.xwlin.source.multi.MultiDataSourceAspect;
import cn.xwlin.source.multi.MultiDatasourceHolder;
import cn.xwlin.source.multi.MultiDatasourceRouter;
import cn.xwlin.source.properties.DatasourceProperties;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Configuration
@EnableConfigurationProperties(DatasourceProperties.class)
@ConditionalOnClass({DataSource.class})
public class DataSourceAutoConfiguration {
    private final DatasourceProperties datasourceProperties;

    public DataSourceAutoConfiguration(DatasourceProperties datasourceProperties) {
        this.datasourceProperties = datasourceProperties;
    }

    @Bean("dataSource")
    public DataSource dataSource() {
        preCheckDataSourceProperties(datasourceProperties);
        MultiDatasourceRouter multiDatasourceRouter = new MultiDatasourceRouter();
        // 如果只有一个数据源的话，那么就是默认的数据源
        Map<String, DatasourceConfig> datasourceConfigMap = datasourceProperties.getConfig();
        Set<String> datasourceKeys = datasourceConfigMap.keySet();


        Map<Object, Object> targetDataSources = new HashMap<>();
        if (datasourceProperties.getConfig().size() == 1) {
            String onlyOneKey = datasourceKeys.stream().findFirst().get().toUpperCase();
            DatasourceConfig datasourceConfig = datasourceConfigMap.get(datasourceKeys.stream().findFirst().get());
            DataSource dataSource = buildDruidDataSource(datasourceConfig, datasourceProperties.getDriverClass());
            targetDataSources.put(onlyOneKey, dataSource);

            multiDatasourceRouter.setDefaultTargetDataSource(dataSource);
            multiDatasourceRouter.setTargetDataSources(targetDataSources);
            multiDatasourceRouter.afterPropertiesSet();

            MultiDatasourceHolder.setDefaultDsName(onlyOneKey);
            return multiDatasourceRouter;
        }

        DataSource defaultDataSource = null;
        for (Map.Entry<String, DatasourceConfig> stringDatasourceConfigEntry : datasourceConfigMap.entrySet()) {
            String key = stringDatasourceConfigEntry.getKey().toUpperCase();
            DatasourceConfig value = stringDatasourceConfigEntry.getValue();

            DataSource dataSource = buildDruidDataSource(value, datasourceProperties.getDriverClass());
            targetDataSources.put(key, dataSource);

            if (value.getPrimary()) {
                MultiDatasourceHolder.setDefaultDsName(key);
                defaultDataSource = dataSource;
            }
        }

        multiDatasourceRouter.setTargetDataSources(targetDataSources);
        if (defaultDataSource != null) {
            multiDatasourceRouter.setDefaultTargetDataSource(defaultDataSource);
        }
        multiDatasourceRouter.afterPropertiesSet();
        return multiDatasourceRouter;
    }

    @Bean("multiDataSourceAspect")
    public MultiDataSourceAspect multiDataSourceAspect() {
        return new MultiDataSourceAspect();
    }

    private DataSource buildDruidDataSource(DatasourceConfig datasourceConfig, String driverClassName) {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUsername(datasourceConfig.getUsername());
        druidDataSource.setPassword(datasourceConfig.getPassword());
        druidDataSource.setDriverClassName(driverClassName);
        druidDataSource.setUrl(datasourceConfig.getUrl());
        druidDataSource.setInitialSize(datasourceConfig.getInitSize());
        druidDataSource.setMinIdle(datasourceConfig.getMinIdle());
        druidDataSource.setMaxActive(datasourceConfig.getMaxActive());
        druidDataSource.setMaxWait(datasourceConfig.getMaxWait());
        if (datasourceConfig.getValidationQuery() != null && "".equals(datasourceConfig.getValidationQuery())) {
            druidDataSource.setValidationQuery(datasourceConfig.getValidationQuery());
            druidDataSource.setValidationQueryTimeout(datasourceConfig.getValidationQueryTimeOut());
        }
        return druidDataSource;
    }

    private void preCheckDataSourceProperties(DatasourceProperties datasourceProperties) {
        if (datasourceProperties.getDriverClass() == null || "".equalsIgnoreCase(datasourceProperties.getDriverClass())) {
            throw new RuntimeException("DriverClass Not be null");
        }
        Map<String, DatasourceConfig> config = datasourceProperties.getConfig();
        if (config == null || config.size() == 0) {
            throw new RuntimeException("No datasource.config");
        }

        long count = config.entrySet().stream().filter(e -> e.getValue().getPrimary()).count();
        if (count > 1) {
            throw new RuntimeException("Just support One primary DataSource");
        }
    }


}