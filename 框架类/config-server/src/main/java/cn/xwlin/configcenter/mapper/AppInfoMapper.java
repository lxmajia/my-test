package cn.xwlin.configcenter.mapper;

import cn.xwlin.configcenter.entity.AppInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AppInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AppInfo record);

    int insertSelective(AppInfo record);

    AppInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AppInfo record);

    int updateByPrimaryKey(AppInfo record);

    AppInfo selectByAppModule(@Param("appCode") String appCode,@Param("moduleCode") String moduleCode);

    List<AppInfo> listAll();
}