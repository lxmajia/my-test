package cn.xwlin;

import cn.xwlin.socket.SocketServer;
import com.iohao.game.action.skeleton.core.IoGameGlobalSetting;
import com.iohao.game.action.skeleton.core.codec.JsonDataCodec;
import com.iohao.game.action.skeleton.ext.spring.ActionFactoryBeanForSpring;
import com.iohao.game.bolt.broker.core.common.IoGameGlobalConfig;
import com.iohao.game.external.core.config.ExternalGlobalConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ExternalServerStart {

    public static void main(String[] args) {
        // 启动 spring boot
        ConfigurableApplicationContext run = SpringApplication.run(ExternalServerStart.class, args);

        // 设置 json 编解码。如果不做设置，默认使用 jprotobuf
        IoGameGlobalConfig.openTraceId = true;
        ExternalGlobalConfig.enableLoggerHandler = true;
        IoGameGlobalSetting.setDataCodec(new JsonDataCodec());

        SocketServer socketServer = run.getBean(SocketServer.class);
        if(socketServer != null){
            socketServer.start();
        }
    }

    @Bean
    public ActionFactoryBeanForSpring actionFactoryBean() {
        // 将业务框架交给 spring 管理
        return ActionFactoryBeanForSpring.me();
    }
}
