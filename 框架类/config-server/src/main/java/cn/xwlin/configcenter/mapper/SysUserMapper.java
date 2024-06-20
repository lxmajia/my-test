package cn.xwlin.configcenter.mapper;

import cn.xwlin.configcenter.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserMapper {

    SysUser selectByLogin(@Param("username") String username, @Param("password") String password);
}