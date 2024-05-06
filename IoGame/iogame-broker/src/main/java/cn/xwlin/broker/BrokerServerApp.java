package cn.xwlin.broker;

import com.iohao.game.bolt.broker.core.common.IoGameGlobalConfig;
import com.iohao.game.bolt.broker.server.BrokerServer;
import com.iohao.game.bolt.broker.server.BrokerServerBuilder;

public class BrokerServerApp {
    public static void main(String[] args) throws Exception {
        // Broker Server （游戏网关服） 构建器
        BrokerServerBuilder brokerServerBuilder = BrokerServer.newBuilder()
                // broker （游戏网关）默认端口 10200
                .port(IoGameGlobalConfig.brokerPort)
                ;
        // 构建游戏网关
        BrokerServer brokerServer = brokerServerBuilder.build();
        // 启动 游戏网关
        brokerServer.startup();
    }
}