package cn.xwlin.dubbo.client.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserInfoDao {
    @Select("select wx_name from user_info where id = 1;")
    String getName();
}
