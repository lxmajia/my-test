package cn.xwlin.mapper;

import cn.xwlin.entity.OrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderInfoMapper {
    int insert(OrderInfo record);

    OrderInfo selectByPrimaryKey(Long id);

    List<OrderInfo> findList(@Param("idList") List<Long> idList);

    List<OrderInfo> listOrder(@Param("userId") Integer userId);

    List<Integer> findTestAll();
}