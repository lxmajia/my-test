package cn.xwlin.configcenter.mapper;

import cn.xwlin.configcenter.entity.AppInfo;
import cn.xwlin.configcenter.vo.request.AppModuleListRequest;
import cn.xwlin.configcenter.vo.resp.AppModuleResp;
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

    List<AppModuleResp> listAll(@Param("appCode") String appCode,@Param("moduleCode") String moduleCode);
}