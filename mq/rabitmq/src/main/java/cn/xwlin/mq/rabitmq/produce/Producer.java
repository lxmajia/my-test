package cn.xwlin.mq.rabitmq.produce;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {

  @Autowired
  private RabbitTemplate rabbitTemplate;

  public void produce(String topic, String msg) {
    System.out.println("发消息：" + topic + ",msg:" + msg);
    rabbitTemplate.convertAndSend(topic, msg);
  }
}