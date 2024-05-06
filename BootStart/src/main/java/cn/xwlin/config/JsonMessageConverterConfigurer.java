package cn.xwlin.config;

import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.support.config.FastJsonConfig;
import com.alibaba.fastjson2.support.spring.http.converter.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class JsonMessageConverterConfigurer implements WebMvcConfigurer {
  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    FastJsonConfig config = new FastJsonConfig();
    config.setCharset(StandardCharsets.UTF_8);
    // 时间格式化
    config.setDateFormat("yyyy-MM-dd HH:mm:ss");
    // 配置序列化的行为(按需配置)
    config.setWriterFeatures(
            JSONWriter.Feature.PrettyFormat,
            JSONWriter.Feature.WriteNullStringAsEmpty,
            JSONWriter.Feature.WriteNullListAsEmpty);

    List<MediaType> fastMediaTypes = new ArrayList<>();
    fastMediaTypes.add(MediaType.APPLICATION_JSON);

    FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
    converter.setFastJsonConfig(config);
    converter.setSupportedMediaTypes(fastMediaTypes);
    converter.setDefaultCharset(StandardCharsets.UTF_8);

    converters.add(0, converter);
  }
}