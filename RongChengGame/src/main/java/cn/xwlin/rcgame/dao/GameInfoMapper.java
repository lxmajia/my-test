package cn.xwlin.rcgame.dao;

import cn.xwlin.rcgame.entity.GameInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GameInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GameInfo record);

    int insertSelective(GameInfo record);

    GameInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GameInfo record);

    int updateByPrimaryKey(GameInfo record);
}