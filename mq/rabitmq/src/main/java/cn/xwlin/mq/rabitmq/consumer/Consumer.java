package cn.xwlin.mq.rabitmq.consumer;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
  @RabbitHandler
  @RabbitListener(queuesToDeclare = @Queue("order_info"))
  public void process(String message) {
    System.out.println("收到消息：" + message);
  }
}