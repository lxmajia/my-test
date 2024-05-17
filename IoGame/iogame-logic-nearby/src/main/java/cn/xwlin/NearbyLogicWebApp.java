package cn.xwlin;

import cn.xwlin.logic.nearby.PositionLogicServer;
import com.iohao.game.action.skeleton.core.IoGameGlobalSetting;
import com.iohao.game.action.skeleton.core.codec.JsonDataCodec;
import com.iohao.game.action.skeleton.ext.spring.ActionFactoryBeanForSpring;
import com.iohao.game.bolt.broker.client.BrokerClientApplication;
import com.iohao.game.bolt.broker.core.common.IoGameGlobalConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author xiang.liao
 * @create 2024/5/17
 */
@SpringBootApplication
public class NearbyLogicWebApp {
  public static void main(String[] args) {
    // 启动 spring boot
    ConfigurableApplicationContext run = SpringApplication.run(NearbyLogicWebApp.class, args);

    // 设置 json 编解码。如果不做设置，默认使用 jprotobuf
    PositionLogicServer bean = run.getBean(PositionLogicServer.class);
    if (bean != null) {
      IoGameGlobalConfig.openTraceId = true;
      IoGameGlobalSetting.setDataCodec(new JsonDataCodec());
      BrokerClientApplication.start(bean);
    }
  }

  @Bean
  public ActionFactoryBeanForSpring actionFactoryBean() {
    // 将业务框架交给 spring 管理
    return ActionFactoryBeanForSpring.me();
  }
}
