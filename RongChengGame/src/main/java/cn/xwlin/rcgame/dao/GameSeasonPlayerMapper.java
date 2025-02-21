package cn.xwlin.rcgame.dao;

import cn.xwlin.rcgame.entity.GameSeasonPlayer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GameSeasonPlayerMapper {
  int deleteByPrimaryKey(Integer id);

  int insert(GameSeasonPlayer record);

  int insertSelective(GameSeasonPlayer record);

  GameSeasonPlayer selectByPrimaryKey(Integer id);

  GameSeasonPlayer selectBySeasonAndNum(@Param("seasonId") Integer seasonId,@Param("clubId") Integer clubId,
                                        @Param("seasonWearNum") Integer seasonWearNum);
  List<GameSeasonPlayer> selectBySeasonAndNumList(@Param("seasonId") Integer seasonId, @Param("clubId") Integer clubId,
                                              @Param("numList") List<Integer> numList);

  int updateByPrimaryKeySelective(GameSeasonPlayer record);

  int updateByPrimaryKey(GameSeasonPlayer record);
}