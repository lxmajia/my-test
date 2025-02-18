package cn.xwlin.rcgame.dao;

import cn.xwlin.rcgame.entity.GameType;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GameTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GameType record);

    int insertSelective(GameType record);

    GameType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GameType record);

    int updateByPrimaryKey(GameType record);
}