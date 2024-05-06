package cn.xwlin.mapper;

import cn.xwlin.entity.WxMsg;

public interface WxMsgMapper {
    
    int deleteByPrimaryKey(Long id);

    
    int insert(WxMsg record);

    
    int insertSelective(WxMsg record);

    WxMsg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WxMsg record);


    int updateByPrimaryKey(WxMsg record);
}