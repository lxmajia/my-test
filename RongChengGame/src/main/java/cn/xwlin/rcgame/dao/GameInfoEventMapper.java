package cn.xwlin.rcgame.dao;

import cn.xwlin.rcgame.entity.GameInfoEvent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GameInfoEventMapper {
  int deleteByPrimaryKey(Integer id);
  int deleteByGameId(@Param("gameId") Integer gameId);

  int insertSelective(GameInfoEvent record);

  GameInfoEvent selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(GameInfoEvent record);

  int updateByPrimaryKey(GameInfoEvent record);

  GameInfoEvent selectByGamePlayerEvent(@Param("gameId") Integer gameId, @Param("gameInfoPlayerId") Integer gameInfoPlayerId, @Param("eventType") Integer eventType);
}