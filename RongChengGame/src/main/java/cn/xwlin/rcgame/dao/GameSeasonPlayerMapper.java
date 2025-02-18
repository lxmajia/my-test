package cn.xwlin.rcgame.dao;

import cn.xwlin.rcgame.entity.GameSeasonPlayer;
import org.apache.ibatis.annotations.Param;

public interface GameSeasonPlayerMapper {
  int deleteByPrimaryKey(Integer id);

  int insert(GameSeasonPlayer record);

  int insertSelective(GameSeasonPlayer record);

  GameSeasonPlayer selectByPrimaryKey(Integer id);

  GameSeasonPlayer selectBySeasonAndNum(@Param("seasonId") Integer seasonId, @Param("seasonWearNum") Integer seasonWearNum);

  int updateByPrimaryKeySelective(GameSeasonPlayer record);

  int updateByPrimaryKey(GameSeasonPlayer record);
}