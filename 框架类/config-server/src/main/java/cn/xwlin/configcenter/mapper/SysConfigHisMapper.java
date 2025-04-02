package cn.xwlin.configcenter.mapper;

import cn.xwlin.configcenter.entity.SysConfigHis;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysConfigHisMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysConfigHis record);

    int insertSelective(SysConfigHis record);

    SysConfigHis selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysConfigHis record);

    int updateByPrimaryKey(SysConfigHis record);
}