package cn.xwlin.rcgame.dao;

import cn.xwlin.rcgame.entity.GameSeason;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GameSeasonMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GameSeason record);

    int insertSelective(GameSeason record);

    GameSeason selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GameSeason record);

    int updateByPrimaryKey(GameSeason record);
}