package cn.xwlin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfig {

  @Bean("httpComponentsClientHttpRequestFactory")
  public HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory(){
    HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
    httpComponentsClientHttpRequestFactory.setReadTimeout(50000);
    httpComponentsClientHttpRequestFactory.setConnectTimeout(10000);
    return httpComponentsClientHttpRequestFactory;
  }

  @Bean("restTemplate")
  public RestTemplate restTemplate(HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory){
    return new RestTemplate(httpComponentsClientHttpRequestFactory);
  }
}
