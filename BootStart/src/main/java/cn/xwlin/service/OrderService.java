package cn.xwlin.service;

import cn.xwlin.constans.DsName;
import cn.xwlin.dao.OrderSeqMapper;
import cn.xwlin.source.multi.DS;
import cn.xwlin.util.IdGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@DS(DsName.ORDER)
public class OrderService {

    @Autowired
    private OrderSeqMapper orderSeqMapper;

    public Long getOrderId(String tableName) {
        Long l = IdGenerate.generateId(tableName);
        return l;
    }
}
