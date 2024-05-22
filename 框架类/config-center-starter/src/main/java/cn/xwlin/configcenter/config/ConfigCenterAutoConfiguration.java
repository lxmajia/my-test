package cn.xwlin.configcenter.config;

import cn.xwlin.configcenter.helper.CfgHelper;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ConfigCenterConfig.class)
public class ConfigCenterAutoConfiguration {
    private final ConfigCenterConfig configCenterConfig;

    public ConfigCenterAutoConfiguration(ConfigCenterConfig configCenterConfig) {
        this.configCenterConfig = configCenterConfig;
    }

    @Bean
    public CfgHelper cfgHelper() {
        return new CfgHelper(configCenterConfig);
    }

}