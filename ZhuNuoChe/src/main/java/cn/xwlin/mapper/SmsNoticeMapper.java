package cn.xwlin.mapper;

import org.apache.ibatis.annotations.Mapper;
import cn.xwlin.entity.SmsNotice;

@Mapper
public interface SmsNoticeMapper {
    
    int deleteByPrimaryKey(Long id);

    
    int insert(SmsNotice record);

    
    int insertSelective(SmsNotice record);

    
    SmsNotice selectByPrimaryKey(Long id);

    
    int updateByPrimaryKeySelective(SmsNotice record);

    
    int updateByPrimaryKey(SmsNotice record);
}