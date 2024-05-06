package cn.xwlin.rcgame.dao;

import cn.xwlin.rcgame.entity.GamePlayInfo;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GamePlayInfoMapper extends BaseMapper<GamePlayInfo> {

    GamePlayInfo selectWithId(@Param("id")Long id);
    GamePlayInfo findByZbbId(@Param("zbbId")Long zbbId);
}