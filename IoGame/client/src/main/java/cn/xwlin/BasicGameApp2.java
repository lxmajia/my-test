package cn.xwlin;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class BasicGameApp2 extends GameApplication {
    private Entity myPlayer;
    Map<String,Entity> playerMap = new HashMap<>();

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(800);
        settings.setHeight(800);
        settings.setTitle("22222");
    }

    @Override
    protected void initGame() {
        myPlayer = FXGL.entityBuilder()
                .at(700, 700)
                .view(new Rectangle(25, 25, Color.RED))
                .buildAndAttach();
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



}