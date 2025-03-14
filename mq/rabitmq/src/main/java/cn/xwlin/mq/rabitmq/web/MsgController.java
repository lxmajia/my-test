package cn.xwlin.mq.rabitmq.web;

import cn.xwlin.mq.rabitmq.produce.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiang.liao
 * @create 2024/10/16
 */
@RestController
@RequestMapping("mq")
public class MsgController {
  @Autowired
  private Producer producer;

  @RequestMapping("send")
  public String send(String topic, String msg) {
    producer.produce(topic, msg);
    return "success";
  }

}
