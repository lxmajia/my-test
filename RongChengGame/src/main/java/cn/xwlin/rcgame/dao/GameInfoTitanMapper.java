package cn.xwlin.rcgame.dao;

import cn.xwlin.rcgame.entity.GameInfoTitan;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GameInfoTitanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GameInfoTitan record);

    int insertSelective(GameInfoTitan record);

    GameInfoTitan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GameInfoTitan record);

    int updateByPrimaryKey(GameInfoTitan record);
}