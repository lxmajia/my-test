package cn.xwlin.configcenter.config;

import cn.xwlin.configcenter.helper.CfgHelper;
import cn.xwlin.configcenter.holder.ConfigCenterConfigHold;
import cn.xwlin.configcenter.timer.ConfigFetchNetwork;
import cn.xwlin.configcenter.vo.HttpResp;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
@EnableConfigurationProperties(ConfigCenterConfig.class)
public class ConfigCenterAutoConfiguration {
  private final ConfigCenterConfig configCenterConfig;

  public ConfigCenterAutoConfiguration(ConfigCenterConfig configCenterConfig) {
    this.configCenterConfig = configCenterConfig;
  }

  @Bean
  @ConditionalOnMissingBean(CfgHelper.class)
  public CfgHelper cfgHelper() {
    return new CfgHelper();
  }
}