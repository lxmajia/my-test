package cn.xwlin.dao;

import cn.xwlin.entity.OrderId;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderSeqMapper {
    Long insertAndGetId(OrderId orderId);
}
