package cn.xwlin.task.core;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.tio.client.ClientChannelContext;
import org.tio.client.ClientTioConfig;
import org.tio.client.ReconnConf;
import org.tio.client.TioClient;
import org.tio.client.intf.ClientAioHandler;
import org.tio.client.intf.ClientAioListener;
import org.tio.core.Node;
import org.tio.core.Tio;
import cn.xwlin.task.core.c2svo.BindGroupMqVO;
import cn.xwlin.task.core.c2svo.C2SVO;
import cn.xwlin.task.core.common.SocketPacket;
import cn.xwlin.task.core.enums.C2SMsgType;
import cn.xwlin.task.core.handler.TaskClientDispatcher;
import cn.xwlin.task.core.holder.TaskServerHolder;
import cn.xwlin.task.core.util.GroupIdUtil;

import java.io.UnsupportedEncodingException;


@Slf4j
public class TaskServer {
    //服务器节点
    private Node serverNode;
    //handler, 包括编码、解码、消息处理
    private ClientAioHandler tioClientHandler = new TaskClientDispatcher();
    //事件监听器，可以为null，但建议自己实现该接口，可以参考showcase了解些接口
    private ClientAioListener aioListener = null;
    //断链后自动连接的，不想自动连接请设为null
    private ReconnConf reconnConf = new ReconnConf(5000L);
    //一组连接共用的上下文对象
    private ClientTioConfig clientTioConfig = new ClientTioConfig(tioClientHandler, aioListener, reconnConf);
    private TioClient tioClient = null;
    private ClientChannelContext clientChannelContext = null;

    private String clientGroupId;

    public TaskServer(String serverUrl, Integer port, String projectId, String appId) {
        log.info("#TaskServer#Init#url:{}#port:{}#", serverUrl, port);
        this.serverNode = new Node(serverUrl, port);
        this.clientGroupId = GroupIdUtil.getGroupId(projectId, appId);
        start();
    }

    /**
     * 启动程序入口
     */
    private void start() {
        log.info("#TaskServer#Start#");
        clientTioConfig.setName(this.clientGroupId);
        clientTioConfig.setHeartbeatTimeout(2000);
        try {
            tioClient = new TioClient(clientTioConfig);
            clientChannelContext = tioClient.connect(serverNode);
        } catch (Exception e) {
            throw new RuntimeException("Can not link to taskServer:" + this.serverNode.getIp());
        }
        //连上后，发条消息玩玩
        try {
            bindGroup();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Can not send Bind Group:" + this.clientGroupId);
        }
        TaskServerHolder.taskServer = this;
    }

    private void bindGroup() throws UnsupportedEncodingException {
        BindGroupMqVO bindGroupMqVO = new BindGroupMqVO();
        bindGroupMqVO.setGroupName(this.clientGroupId);

        C2SVO c2SVO = new C2SVO();
        c2SVO.setC2sType(C2SMsgType.BIND_APP);
        c2SVO.setParam(JSON.toJSONString(bindGroupMqVO));

        SocketPacket packet = new SocketPacket();
        packet.setBody(JSON.toJSONString(c2SVO).getBytes(SocketPacket.CHARSET));
        Tio.send(clientChannelContext, packet);
    }

    public static void reportParam(String key, String value) {
        TaskServer taskServer = TaskServerHolder.taskServer;
        
    }
}
