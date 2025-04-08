package cn.xwlin.configcenter.mapper;

import cn.xwlin.configcenter.entity.ConfigInfoHis;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConfigInfoHisMapper {
  int deleteByPrimaryKey(Long id);

  int insert(ConfigInfoHis record);

  int insertSelective(ConfigInfoHis record);

  ConfigInfoHis selectByPrimaryKey(Long id);

  int updateByPrimaryKeySelective(ConfigInfoHis record);

  int updateByPrimaryKey(ConfigInfoHis record);
}