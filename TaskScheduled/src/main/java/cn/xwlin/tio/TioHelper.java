package cn.xwlin.tio;

import cn.xwlin.tio.common.Const;
import cn.xwlin.tio.common.LlJobPacket;
import cn.xwlin.tio.server.ServerMsgAioDispacher;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.server.ServerTioConfig;
import org.tio.server.TioServer;
import org.tio.server.intf.ServerAioHandler;
import org.tio.server.intf.ServerAioListener;
import org.tio.utils.lock.SetWithLock;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Component
public class TioHelper {

    //handler, 包括编码、解码、消息处理
    public static ServerAioHandler aioHandler = new ServerMsgAioDispacher();
    //事件监听器，可以为null，但建议自己实现该接口，可以参考showcase了解些接口
    public static ServerAioListener aioListener = null;
    //一组连接共用的上下文对象
    public static ServerTioConfig serverTioConfig = new ServerTioConfig("hello-tio-server", aioHandler, aioListener);
    //tioServer对象
    public static TioServer tioServer = new TioServer(serverTioConfig);
    //有时候需要绑定ip，不需要则null
    public static String serverIp = null;
    //监听的端口
    public static int serverPort = Const.PORT;

    /**
     * 启动程序入口
     */
    public TioHelper() throws IOException {
        serverTioConfig.setHeartbeatTimeout(Const.TIMEOUT);
        tioServer.start(serverIp, serverPort);
    }

    public boolean sendHandler(String proId, String appId, String params) {
        Optional<ChannelContext> channelByGroup = getChannelByGroup(proId, appId);
        if (channelByGroup.isPresent()) {
            ChannelContext channelContext = channelByGroup.get();
            log.info("#TriggerTask#pro:{}#app:{}#ip:{}#prm:{}", proId, appId,
                    channelContext.getClientNode().getIp(),
                    JSON.toJSONString(params));
            LlJobPacket resppacket = new LlJobPacket();
            try {
                resppacket.setBody(JSON.toJSONString(params).getBytes(LlJobPacket.CHARSET));
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
            Tio.send(channelContext, resppacket);
        }
        return true;
    }

    private Optional<ChannelContext> getChannelByGroup(String proId, String appId) {
        SetWithLock<ChannelContext> channelContextsByGroup = Tio.getChannelContextsByGroup(serverTioConfig, getGroupName(proId, appId));
        if (channelContextsByGroup == null || channelContextsByGroup.size() <= 0) {
            return Optional.empty();
        }
        Set<ChannelContext> obj = channelContextsByGroup.getObj();
        if (CollectionUtils.isEmpty(obj)) {
            return Optional.empty();
        }
        return obj.stream().findAny();
    }

    private String getGroupName(String proId, String appId) {
        return proId + "=" + appId;
    }
}
