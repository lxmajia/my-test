package cn.xwlin.rcgame.dao;

import cn.xwlin.rcgame.entity.GameInfoPlayer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GameInfoPlayerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GameInfoPlayer record);

    int insertSelective(GameInfoPlayer record);

    GameInfoPlayer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GameInfoPlayer record);

    int updateByPrimaryKey(GameInfoPlayer record);
}