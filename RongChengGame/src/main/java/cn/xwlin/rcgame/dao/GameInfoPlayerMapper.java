package cn.xwlin.rcgame.dao;

import cn.xwlin.rcgame.entity.GameInfoPlayer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GameInfoPlayerMapper {
    int deleteByPrimaryKey(Integer id);
    int deleteByGameId(@Param("gameId") Integer gameId);


    int insert(GameInfoPlayer record);

    int insertSelective(GameInfoPlayer record);

    GameInfoPlayer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GameInfoPlayer record);

    int updateByPrimaryKey(GameInfoPlayer record);

    GameInfoPlayer selectBySeasonAndNum(@Param("seasonId") Integer seasonId,@Param("clubId") Integer clubId, @Param("seasonWearNum") Integer seasonWearNum);
}