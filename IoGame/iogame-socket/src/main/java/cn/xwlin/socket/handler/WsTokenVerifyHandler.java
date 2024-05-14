package cn.xwlin.socket.handler;

import cn.xwlin.web.util.SocketConnectUtil;
import com.iohao.game.action.skeleton.core.DataCodecKit;
import com.iohao.game.action.skeleton.protocol.wrapper.StringValue;
import com.iohao.game.external.core.netty.handler.ws.WebSocketVerifyHandler;
import com.iohao.game.external.core.netty.session.SocketUserSession;
import com.iohao.game.external.core.session.UserChannelId;
import com.iohao.game.external.core.session.UserSessionOption;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

public class WsTokenVerifyHandler extends WebSocketVerifyHandler {

    @Override
    public boolean verify(SocketUserSession userSession
                          , Map<String, String> params) {
        /*
         * 测试方法，ws://127.0.0.1:10100/websocket?token=abc&userId=132456789
         * 之后可以在 params 中得到参数 key:value 格式
         */
        String token = params.get("token");
        String userId = params.get("userId");
        // 验证token和密码
        boolean verifyResult = StringUtils.isNotBlank(token) && StringUtils.isNotBlank(userId);
        long userIdLong = Long.valueOf(userId);

        Long userIdByToken =  SocketConnectUtil.getUserService().userIdByToken(token);
        verifyResult = userIdByToken != null && userIdByToken.equals(userIdLong);

        /*
         * 为了简单演示，这里只将 token 放到元信息中，
         * 在实际中，开发者可以自定义一个元信息类，来存放更复杂的数据
         */
        StringValue tokenValue = StringValue.of(token);
        byte[] encode = DataCodecKit.encode(tokenValue);
        userSession.option(UserSessionOption.attachment, encode);

        // 设置玩家的 userId，之后就不需要在游戏逻辑服中设置了，少一个环节。
        UserChannelId userChannelId = userSession.getUserChannelId();

        verifyResult = verifyResult && this.userSessions.settingUserId(userChannelId, userIdLong);
        // 返回 true 表示验证通过，返回 false 框架会关闭连接。
        System.out.println("verifyResult:" + verifyResult);
        return verifyResult;
    }
}