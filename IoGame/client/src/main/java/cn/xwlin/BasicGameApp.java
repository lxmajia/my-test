package cn.xwlin;

import cn.xwlin.common.action.WsActionResp;
import cn.xwlin.nearBy.NearPlayerInfo;
import cn.xwlin.nearBy.NearPlayerReq;
import cn.xwlin.nearBy.NearPlayerResp;
import com.alibaba.fastjson2.JSONObject;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.iohao.game.action.skeleton.core.DataCodecKit;
import com.iohao.game.external.core.message.ExternalMessage;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lombok.SneakyThrows;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.math.BigDecimal;
import java.net.URI;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class BasicGameApp extends GameApplication {
  private Entity myPlayer;
  Map<Long, Entity> playerMap = new Hashtable<>();

  @Override
  protected void initSettings(GameSettings settings) {
    settings.setWidth(800);
    settings.setHeight(800);
    settings.setTitle("1111");
  }

  @Override
  protected void initGame() {
    myPlayer = FXGL.entityBuilder().at(100, 100).view(new Rectangle(25, 25, Color.BLUE)).buildAndAttach();
  }

  @Override
  protected void initInput() {
    FXGL.onKey(KeyCode.D, () -> {
      myPlayer.translateX(5); // move right 5 pixels
    });

    FXGL.onKey(KeyCode.A, () -> {
      myPlayer.translateX(-5); // move left 5 pixels
    });

    FXGL.onKey(KeyCode.W, () -> {
      myPlayer.translateY(-5); // move up 5 pixels
    });

    FXGL.onKey(KeyCode.S, () -> {
      myPlayer.translateY(5); // move down 5 pixels
    });
  }

  public static void main(String[] args) {
    launch(args);
  }

  public void goWebSocket(String token, Long userId) {
    try {
      URI uri = new URI("ws://example.com/websocket"); // 替换为你的WebSocket服务器URI
      WebSocketClient webSocketClient = new WebSocketClient(uri) {
        @Override
        public void onOpen(ServerHandshake handshakedata) {
          System.out.println("新连接已打开");

          new Thread(() -> {
            double x = myPlayer.getX();
            double y = myPlayer.getY();

            while (true) {
              try {
                Thread.sleep(100);
              } catch (InterruptedException e) {
              }
              byte[] bytes = initRequest((int) x, (int) y);
              this.send(bytes);
            }
          }).start();
        }

        @Override
        public void onMessage(String message) {
          WsActionResp<NearPlayerResp> wsActionResp = JSONObject.parseObject(message, WsActionResp.class);
          if (wsActionResp.getCode() == 0) {
            List<NearPlayerInfo> nearPlayerInfoList = wsActionResp.getBody().getNearPlayerInfoList();
            for (NearPlayerInfo nearPlayerInfo : nearPlayerInfoList) {
              Entity entity = playerMap.get(nearPlayerInfo.getUserId());
              if (entity == null) {
                myPlayer = FXGL.entityBuilder().at(100, 100).view(new Rectangle(25, 25, Color.BLUE)).buildAndAttach();
                playerMap.put(nearPlayerInfo.getUserId(), entity);
              } else {
                myPlayer.translate(nearPlayerInfo.getLongitude().doubleValue(), nearPlayerInfo.getLatitude().doubleValue());
              }
            }
          }
        }

        @Override
        public void onClose(int code, String reason, boolean remote) {
          System.out.println("连接已关闭");
        }

        @Override
        public void onError(Exception ex) {
          ex.printStackTrace();
        }
      };
      webSocketClient.connect(); // 连接到WebSocket服务器
      // 在此处添加代码，处理你的业务逻辑
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static byte[] initRequest(int longitude, int latitude) {
    int cmdMerge = 131073;

    NearPlayerReq nearPlayerReq = new NearPlayerReq();
    nearPlayerReq.setLongitude(BigDecimal.valueOf(longitude));
    nearPlayerReq.setLatitude(BigDecimal.valueOf(latitude));

    ExternalMessage externalMessage = new ExternalMessage();
    externalMessage.setCmdCode(1);
    externalMessage.setCmdMerge(cmdMerge);
    externalMessage.setData(DataCodecKit.encode(nearPlayerReq));
    byte[] encode = DataCodecKit.encode(externalMessage);
    return encode;
  }
}