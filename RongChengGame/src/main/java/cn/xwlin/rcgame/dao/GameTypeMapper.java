package cn.xwlin.rcgame.dao;

import cn.xwlin.rcgame.entity.GameKind;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GameTypeMapper  extends BaseMapper<GameKind> {
}