package cn.xwlin.mapper;

import cn.xwlin.entity.TelNotice;

public interface TelNoticeMapper {
    
    int deleteByPrimaryKey(Long id);

    
    int insert(TelNotice record);

    
    int insertSelective(TelNotice record);

    
    TelNotice selectByPrimaryKey(Long id);

    
    int updateByPrimaryKeySelective(TelNotice record);

    
    int updateByPrimaryKey(TelNotice record);
}