package cn.xwlin.socket;

import cn.xwlin.common.action.DemoCmd;
import cn.xwlin.common.proto.HelloReq;
import com.iohao.game.action.skeleton.core.IoGameGlobalSetting;
import com.iohao.game.action.skeleton.core.codec.JsonDataCodec;
import com.iohao.game.common.kit.concurrent.TaskKit;
import com.iohao.game.external.client.AbstractInputCommandRegion;
import com.iohao.game.external.client.InputCommandRegion;
import com.iohao.game.external.client.join.ClientRunOne;
import com.iohao.game.external.client.kit.ClientUserConfigs;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ClientTest {
    public static void main(String[] args) {
        // 使用 json 编解码
        IoGameGlobalSetting.setDataCodec(new JsonDataCodec());

        ClientUserConfigs.closeLog();

        // 模拟请求数据
        List<InputCommandRegion> inputCommandRegions = List.of(
                new InternalRegion()
        );

        // 启动模拟客户端
        new ClientRunOne()
                .setInputCommandRegions(inputCommandRegions)
                .startup();
    }

    static class InternalRegion extends AbstractInputCommandRegion {
        @Override
        public void initInputCommand() {
            // 模拟请求的主路由
            inputCommandCreate.cmd = DemoCmd.cmd;

            // 配置一些模拟请求
            ofCommand(DemoCmd.hello).setTitle("hello").setRequestData(() -> {
                HelloReq helloReq = new HelloReq();
                helloReq.setName("LiaoXiang");
                return helloReq;
            }).callback(result -> {
                HelloReq value = result.getValue(HelloReq.class);
                log.info("value : {}", value);
            });

            // 一秒后，执行模拟请求;
            TaskKit.runOnceSecond(() -> {
                ofRequestCommand(DemoCmd.hello).execute();
            });
        }
    }
}