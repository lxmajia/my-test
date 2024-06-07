package cn.xwlin.configcenter.mapper;

import cn.xwlin.configcenter.entity.ConfigInfo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface ConfigInfoMapper {
  int deleteByPrimaryKey(Long id);

  int insert(ConfigInfo record);

  int insertSelective(ConfigInfo record);

  ConfigInfo selectByPrimaryKey(Long id);

  int updateByPrimaryKeySelective(ConfigInfo record);

  int updateByPrimaryKeyWithBLOBs(ConfigInfo record);

  int updateByPrimaryKey(ConfigInfo record);

  List<ConfigInfo> selectByAppModuleCode(@Param("appCode") String appCode,
                                         @Param("moduleCode") String moduleCode);

  ConfigInfo selectByUniqueKey(@Param("uniqueKey") String uniqueKey);

  List<ConfigInfo> selectByUniqueKeyList(@Param("uniqueKeyList") List<String> uniqueKeyList);

  List<ConfigInfo> listChangeConfig(@Param("modified") Date modified);

  List<ConfigInfo> listAllConfig();
}