package cn.xwlin.socket.handler;

import cn.xwlin.web.util.SocketConnectUtil;
import com.iohao.game.external.core.hook.UserHook;
import com.iohao.game.external.core.session.UserSession;
import com.iohao.game.external.core.session.UserSessionOption;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.nio.charset.StandardCharsets;


@Slf4j
public class UserStatusHook implements UserHook {
    @Override
    public void into(UserSession userSession) {
        long userId = userSession.getUserId();
        byte[] tokenBytes = userSession.getOptions().option(UserSessionOption.attachment);
        String token = new String(tokenBytes, StandardCharsets.UTF_8);
        log.info("玩家上线 userId: {} -- {}", userId, token);
    }

    @Override
    public void quit(UserSession userSession) {
        long userId = userSession.getUserId();
        byte[] tokenBytes = userSession.getOptions().option(UserSessionOption.attachment);
        String token = new String(tokenBytes, StandardCharsets.UTF_8);
        log.info("玩家退出 userId: {} -- {}", userId, token);

        if(StringUtils.isNotBlank(token)){
            SocketConnectUtil.getUserService().logout(token);
        }
    }
}