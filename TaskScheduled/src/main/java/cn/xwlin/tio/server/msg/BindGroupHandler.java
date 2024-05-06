package cn.xwlin.tio.server.msg;

import cn.xwlin.task.core.c2svo.BindGroupMqVO;
import cn.xwlin.task.core.enums.C2SMsgType;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;

@Slf4j
@Component
public class BindGroupHandler extends MsgHandler<BindGroupMqVO> {
    @Override
    public C2SMsgType getHandlerMsgType() {
        return C2SMsgType.BIND_APP;
    }

    @Override
    public void handler(BindGroupMqVO bindGroupMqVO, ChannelContext channelContext) throws Exception {
        System.out.println("BindGroupHandler.handler#msg:" + JSON.toJSONString(bindGroupMqVO));
        String groupName = bindGroupMqVO.getGroupName();
        Tio.bindGroup(channelContext, groupName);
        log.info("#Task#BinGroup#SUCCESS#group:{}", groupName);
    }
}
