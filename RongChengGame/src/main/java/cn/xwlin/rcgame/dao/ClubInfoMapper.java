package cn.xwlin.rcgame.dao;

import cn.xwlin.rcgame.entity.ClubInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ClubInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ClubInfo record);

    int insertSelective(ClubInfo record);

    ClubInfo selectByPrimaryKey(Integer id);
    ClubInfo selectByShowName(@Param("showName") String showName);

    int updateByPrimaryKeySelective(ClubInfo record);

    int updateByPrimaryKey(ClubInfo record);
}