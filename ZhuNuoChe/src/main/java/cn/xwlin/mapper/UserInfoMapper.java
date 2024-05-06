package cn.xwlin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import cn.xwlin.entity.UserInfo;

@Mapper
public interface UserInfoMapper {

  int deleteByPrimaryKey(Long id);


  int insert(UserInfo record);


  int insertSelective(UserInfo record);


  UserInfo selectByPrimaryKey(Long id);


  int updateByPrimaryKeySelective(UserInfo record);


  int updateByPrimaryKey(UserInfo record);

  UserInfo findBySceneCode(@Param("sceneCode") String sceneCode);
  UserInfo findByOpenId(@Param("openId") String openId);
}