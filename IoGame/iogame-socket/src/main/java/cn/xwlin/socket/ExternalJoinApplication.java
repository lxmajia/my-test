package cn.xwlin.socket;

import cn.xwlin.socket.handler.MyWebSocketVerifyHandler;
import com.iohao.game.action.skeleton.core.IoGameGlobalSetting;
import com.iohao.game.action.skeleton.core.codec.JsonDataCodec;
import com.iohao.game.bolt.broker.core.client.BrokerAddress;
import com.iohao.game.bolt.broker.core.common.IoGameGlobalConfig;
import com.iohao.game.external.core.ExternalServer;
import com.iohao.game.external.core.config.ExternalGlobalConfig;
import com.iohao.game.external.core.config.ExternalJoinEnum;
import com.iohao.game.external.core.micro.PipelineContext;
import com.iohao.game.external.core.netty.DefaultExternalCoreSetting;
import com.iohao.game.external.core.netty.DefaultExternalServer;
import com.iohao.game.external.core.netty.DefaultExternalServerBuilder;
import com.iohao.game.external.core.netty.handler.ws.HttpRealIpHandler;
import com.iohao.game.external.core.netty.handler.ws.WebSocketVerifyHandler;
import com.iohao.game.external.core.netty.kit.ExternalServerCreateKit;
import com.iohao.game.external.core.netty.micro.WebSocketMicroBootstrapFlow;

import static com.iohao.game.external.core.config.ExternalGlobalConfig.externalPort;

public class ExternalJoinApplication {

  public void start() {
    int externalCorePort = 10100;
    // 创建游戏对外服构建器
    DefaultExternalServerBuilder builder = DefaultExternalServer
            // 游戏对外服端口；与真实玩家建立连接的端口
            .newBuilder(externalCorePort)
            // 连接方式 WebSocket；默认不填写也是这个值
            .externalJoinEnum(ExternalJoinEnum.WEBSOCKET)
            // 与 Broker （游戏网关）的连接地址 ；默认不填写也是这个值
            .brokerAddress(new BrokerAddress("127.0.0.1", 10200));

    // 设置 MicroBootstrapFlow 类，并重写 createVerifyHandler 方法
    builder.setting().setMicroBootstrapFlow(new WebSocketMicroBootstrapFlow() {
      @Override
      protected WebSocketVerifyHandler createVerifyHandler() {
        return new MyWebSocketVerifyHandler();
      }

      @Override
      protected void httpHandler(PipelineContext context) {
        super.httpHandler(context);
        /*
         * HttpRealIpHandler 是框架内置的一个 handler。
         * 添加上后，即使是通过 nginx 转发，也可以得到玩家真实的 ip
         */
        context.addLast("HttpRealIpHandler", new HttpRealIpHandler());
      }
    });

    // 构建、启动
    ExternalServer externalServer = builder.build();
    externalServer.startup();
  }

  public static void main(String[] args) {
    // 设置 json 编解码。如果不做设置，默认使用 jprotobuf
    IoGameGlobalConfig.openTraceId = true;
    ExternalGlobalConfig.enableLoggerHandler = true;
    IoGameGlobalSetting.setDataCodec(new JsonDataCodec());
    new ExternalJoinApplication().start();
  }
}