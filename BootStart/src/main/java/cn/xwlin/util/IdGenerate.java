package cn.xwlin.util;

import cn.xwlin.dao.OrderSeqMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xiang.liao
 * @create 2024/6/4
 */
@Component
public class IdGenerate {
  private static OrderSeqMapper orderSeqMapper;

  @Autowired
  public void setOrderSeqMapper(OrderSeqMapper orderSeqMapper) {
    IdGenerate.orderSeqMapper = orderSeqMapper;
  }

  public static Long generateId(String tableName) {
    IdBean idBean = new IdBean(tableName);
    orderSeqMapper.insertAndGetId(idBean);
    return idBean.getId();
  }
}
