package cn.xwlin;

import cn.xwlin.common.action.DemoCmd;
import cn.xwlin.common.proto.HelloReq;
import com.iohao.game.external.client.AbstractInputCommandRegion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemoRegion extends AbstractInputCommandRegion {
    static final Logger log = LoggerFactory.getLogger(DemoRegion.class);

    @Override
    public void initInputCommand() {
        // 模拟请求的主路由
        inputCommandCreate.cmd = DemoCmd.cmd;

        // ---------------- 模拟请求 1-0 ----------------
        ofCommand(DemoCmd.hello).setTitle("here").setRequestData(() -> {
            HelloReq helloReq = new HelloReq();
            helloReq.name = "1";
            return helloReq;
        }).callback(result -> {
            HelloReq value = result.getValue(HelloReq.class);
            log.info("value : {}", value);
        });

        // ---------------- 模拟请求 1-1 ----------------
//        ofCommand(DemoCmd.jackson).setTitle("jackson").setRequestData(() -> {
//            HelloReq helloReq = new HelloReq();
//            helloReq.name = "1";
//            return helloReq;
//        }).callback(result -> {
//            // 不会进入到这里，因为发生了异常。 1-1 action 的逻辑要求 name 必须是 jackson。
//            HelloReq value = result.getValue(HelloReq.class);
//            log.info("value : {}", value);
//        });
    }
}