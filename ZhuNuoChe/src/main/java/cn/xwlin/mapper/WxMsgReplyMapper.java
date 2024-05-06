package cn.xwlin.mapper;

import cn.xwlin.entity.WxMsgReply;

public interface WxMsgReplyMapper {
    
    int deleteByPrimaryKey(Long id);

    
    int insert(WxMsgReply record);

    
    int insertSelective(WxMsgReply record);

    
    WxMsgReply selectByPrimaryKey(Long id);

    
    int updateByPrimaryKeySelective(WxMsgReply record);

    
    int updateByPrimaryKey(WxMsgReply record);
}