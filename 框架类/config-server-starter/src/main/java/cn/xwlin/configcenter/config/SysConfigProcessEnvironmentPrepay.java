package cn.xwlin.configcenter.config;

import cn.xwlin.configcenter.holder.ConfigCenterConfigHold;
import cn.xwlin.configcenter.timer.ConfigFetchNetwork;
import cn.xwlin.configcenter.vo.GetConfigData;
import cn.xwlin.configcenter.vo.HttpResp;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.util.StringUtils;

import java.util.Map;


/**
 * @author xiang.liao
 * @create 2024/6/7
 */
public class SysConfigProcessEnvironmentPrepay implements EnvironmentPostProcessor, Ordered {
  @Override
  public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
    String url = StringUtils.hasLength(environment.getProperty("wl.app.url")) ? environment.getProperty("wl.app.url") : ConfigCenterConfigHold.url;
    Integer port = StringUtils.hasLength(environment.getProperty("wl.app.port")) ? Integer.parseInt(environment.getProperty("wl.app.port")) : ConfigCenterConfigHold.port;
    Integer timeout = StringUtils.hasLength(environment.getProperty("wl.app.refresh-config-timeout")) ? Integer.parseInt(environment.getProperty("wl.app.refresh-config-timeout")) : ConfigCenterConfigHold.timeout;

    // 这里执行优先于Autoconfiguration创建Bean，所以在这里做校验和赋值了
    String appCode = environment.getProperty("wl.app.app-code");
    String moduleCode = environment.getProperty("wl.app.module-code");
    ConfigCenterConfigHold.url = url;
    ConfigCenterConfigHold.port = Integer.valueOf(port);
    ConfigCenterConfigHold.appCode = appCode;
    ConfigCenterConfigHold.moduleCode = moduleCode;
    ConfigCenterConfigHold.timeout = timeout;
    checkConfig(url, appCode, moduleCode);

    String sysConfig = ConfigFetchNetwork.getSysConfig();
    if (!StringUtils.hasLength(sysConfig)) {
      throw new RuntimeException("config center:getSysConfig error,response=null");
    }
    TypeReference<HttpResp<GetConfigData>> typeReference = new TypeReference<HttpResp<GetConfigData>>() {
    };
    HttpResp<GetConfigData> getConfigDataHttpResp = JSONObject.parseObject(sysConfig, typeReference);
    if (getConfigDataHttpResp.getCode() != 0) {
      throw new RuntimeException("config center:getSysConfig failed,reason:" + getConfigDataHttpResp.getMessage());
    }
    Map<String, Object> changeConfigMap = getConfigDataHttpResp.getBody().getSysConfigMap();
    if (changeConfigMap != null) {
      MapPropertySource propertySource = new MapPropertySource("XwlConfigCenter", changeConfigMap);
      environment.getPropertySources().addFirst(propertySource);
    }
  }

  private void checkConfig(String url, String appCode, String moduleCode) {
    if (!StringUtils.hasLength(url)) {
      throw new RuntimeException("config center url is null");
    }
    if (!StringUtils.hasLength(appCode)) {
      throw new RuntimeException("config center appCode is null");
    }
    if (!StringUtils.hasLength(moduleCode)) {
      throw new RuntimeException("config center moduleCode is null");
    }
    String checkAppModule = ConfigFetchNetwork.checkAppModule();
    if (!StringUtils.hasLength(checkAppModule)) {
      throw new RuntimeException("config center checkFailed,response=null");
    }
    TypeReference<HttpResp> typeReference = new TypeReference<HttpResp>() {
    };
    HttpResp getConfigDataHttpResp = JSONObject.parseObject(checkAppModule, typeReference);
    if (getConfigDataHttpResp.getCode() != 0) {
      throw new RuntimeException("config center checkFailed,reason:" + getConfigDataHttpResp.getMessage());
    }
  }

  @Override
  public int getOrder() {
    return Integer.MAX_VALUE;
  }
}
