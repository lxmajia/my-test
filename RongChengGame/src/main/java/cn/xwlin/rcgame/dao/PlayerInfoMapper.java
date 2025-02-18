package cn.xwlin.rcgame.dao;

import cn.xwlin.rcgame.entity.PlayerInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlayerInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PlayerInfo record);

    int insertSelective(PlayerInfo record);

    PlayerInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PlayerInfo record);

    int updateByPrimaryKey(PlayerInfo record);
}