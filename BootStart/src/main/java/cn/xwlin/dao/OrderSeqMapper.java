package cn.xwlin.dao;

import cn.xwlin.util.IdBean;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderSeqMapper {
    Long insertAndGetId(IdBean idBean);
}
