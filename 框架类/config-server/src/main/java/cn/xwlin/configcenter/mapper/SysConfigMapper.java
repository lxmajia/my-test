package cn.xwlin.configcenter.mapper;

import cn.xwlin.configcenter.entity.ConfigInfo;
import cn.xwlin.configcenter.entity.SysConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface SysConfigMapper {

  SysConfig selectByPrimaryKey(Long id);

  List<SysConfig> listByAppModuleId(@Param("appModuleId") Long appModuleId,@Param("configKey")String configKey);
}