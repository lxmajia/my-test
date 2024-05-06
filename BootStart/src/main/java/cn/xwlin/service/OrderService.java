package cn.xwlin.service;

import cn.hutool.core.util.PhoneUtil;
import cn.xwlin.constans.DsName;
import cn.xwlin.dao.OrderSeqMapper;
import cn.xwlin.entity.OrderId;
import cn.xwlin.source.multi.DS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@DS(DsName.ORDER)
public class OrderService {

    @Autowired
    private OrderSeqMapper orderSeqMapper;

    public Long getOrderId() {
        OrderId orderId = new OrderId();
        orderSeqMapper.insertAndGetId(orderId);
        return orderId.get();
    }
}
