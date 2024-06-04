package cn.xwlin.service;

import cn.xwlin.seqid.SeqIdUtil;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
  public Long getOrderId(String tableName) {
    Long l = SeqIdUtil.getSequenceId(tableName);
    return l;
  }
}
