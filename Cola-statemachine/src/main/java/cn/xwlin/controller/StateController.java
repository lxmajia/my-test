package cn.xwlin.controller;

import cn.xwlin.config.StateMachineRegister;
import cn.xwlin.cons.OrderEvent;
import cn.xwlin.cons.OrderStatus;
import cn.xwlin.entity.Order;
import com.alibaba.cola.statemachine.StateMachine;
import com.alibaba.cola.statemachine.StateMachineFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiang.liao
 * @create 2024/2/27
 */
@RestController
@RequestMapping("/statue")
public class StateController {
  @RequestMapping("/to")

  public String goStatus(Long orderId, OrderStatus status, OrderEvent event) {
    StateMachine<OrderStatus, OrderEvent, Order> stateMachine = StateMachineFactory.get(StateMachineRegister.ORDER_STATE_MACHINE_ID);
    Order o = new Order();
    o.setId(orderId);
    o.setStatus(status);
    OrderStatus orderStatus = stateMachine.fireEvent(status, event, o);
    return orderStatus.name();
  }
}
