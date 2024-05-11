package cn.xwlin;

import com.iohao.game.external.client.InputCommandRegion;
import com.iohao.game.external.client.join.ClientRunOne;
import com.iohao.game.external.client.kit.ClientUserConfigs;

import java.util.List;

public class Client1 {
    public static void main(String[] args) {
        // 关闭模拟请求相关日志
//        ClientUserConfigs.closeLog();

        // 模拟请求数据
        List<InputCommandRegion> inputCommandRegions = List.of(
                new DemoRegion()
        );

        // 启动模拟客户端
        new ClientRunOne().setConnectAddress("127.0.0.1")
                .setConnectPort(10100)
                .setInputCommandRegions(inputCommandRegions)
                .startup();
    }
}
