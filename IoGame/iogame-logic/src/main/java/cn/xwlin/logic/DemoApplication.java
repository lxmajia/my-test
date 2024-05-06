package cn.xwlin.logic;

import com.iohao.game.action.skeleton.core.doc.BarSkeletonDoc;
import com.iohao.game.action.skeleton.ext.spring.ActionFactoryBeanForSpring;
import com.iohao.game.bolt.broker.client.BrokerClientApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        // 启动 spring boot
        SpringApplication.run(DemoApplication.class, args);
        // 游戏对外服端口
        BrokerClientApplication.start(new DemoLogicServer());
        // 生成对接文档
        BarSkeletonDoc.me().buildDoc();
    }

    @Bean
    public ActionFactoryBeanForSpring actionFactoryBean() {
        // 将业务框架交给 spring 管理
        return ActionFactoryBeanForSpring.me();
    }
}