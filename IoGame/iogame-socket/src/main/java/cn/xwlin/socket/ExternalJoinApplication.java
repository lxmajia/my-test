package cn.xwlin.socket;

import cn.xwlin.socket.handler.MyWebSocketVerifyHandler;
import com.iohao.game.action.skeleton.core.IoGameGlobalSetting;
import com.iohao.game.action.skeleton.core.codec.JsonDataCodec;
import com.iohao.game.bolt.broker.core.client.BrokerAddress;
import com.iohao.game.bolt.broker.core.common.IoGameGlobalConfig;
import com.iohao.game.external.core.ExternalServer;
import com.iohao.game.external.core.config.ExternalJoinEnum;
import com.iohao.game.external.core.netty.DefaultExternalCoreSetting;
import com.iohao.game.external.core.netty.DefaultExternalServer;
import com.iohao.game.external.core.netty.handler.ws.WebSocketVerifyHandler;
import com.iohao.game.external.core.netty.kit.ExternalServerCreateKit;
import com.iohao.game.external.core.netty.micro.WebSocketMicroBootstrapFlow;

import static com.iohao.game.external.core.config.ExternalGlobalConfig.externalPort;

public class ExternalJoinApplication {

  public void start() {
    // 设置 json 编解码。如果不做设置，默认使用 jprotobuf
    IoGameGlobalSetting.setDataCodec(new JsonDataCodec());
    int externalCorePort = 10100;
    // 创建游戏对外服构建器
    var builder = DefaultExternalServer
            // 游戏对外服端口；与真实玩家建立连接的端口
            .newBuilder(externalCorePort)
            // 连接方式 WebSocket；默认不填写也是这个值
            .externalJoinEnum(ExternalJoinEnum.WEBSOCKET)
            // 与 Broker （游戏网关）的连接地址 ；默认不填写也是这个值
            .brokerAddress(new BrokerAddress("127.0.0.1", IoGameGlobalConfig.brokerPort));

    // 得到 setting 对象（开发者可根据自身业务做扩展）
    DefaultExternalCoreSetting setting = builder.setting();
    // 设置 MicroBootstrapFlow 类，并重写 createVerifyHandler 方法
    setting.setMicroBootstrapFlow(new WebSocketMicroBootstrapFlow() {
      @Override
      protected WebSocketVerifyHandler createVerifyHandler() {
        return new MyWebSocketVerifyHandler();
      }
    });
    // 构建、启动
    ExternalServer externalServer = builder.build();
    externalServer.startup();
  }

  public static void main(String[] args) {
    new ExternalJoinApplication().start();
  }
}