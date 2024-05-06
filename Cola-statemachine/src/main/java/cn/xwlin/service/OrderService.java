package cn.xwlin.service;

import cn.xwlin.config.StateMachineRegister;
import cn.xwlin.cons.OrderEvent;
import cn.xwlin.cons.OrderStatus;
import cn.xwlin.entity.Order;
import com.alibaba.cola.statemachine.Action;
import com.alibaba.cola.statemachine.Condition;
import com.alibaba.cola.statemachine.StateMachine;
import com.alibaba.cola.statemachine.StateMachineFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
  public Action<OrderStatus, OrderEvent, Order> saveAction() {
    return (from, to, event, order) -> this.saveOrderStatus(order, from, to);
  }

  public Action<OrderStatus, OrderEvent, Order> submitAction() {
    return (from, to, event, order) -> this.saveOrderStatus(order, from, to);
  }

  public Action<OrderStatus, OrderEvent, Order> sealSucceedAction() {
    return (from, to, event, order) -> this.saveOrderStatus(order, from, to);
  }

  private void saveOrderStatus(Order order, OrderStatus fromStatus, OrderStatus toStatus) {
    order.setStatus(toStatus);
    System.out.println("OrderId:" + order.getId() + "  statusChange:From[" + fromStatus.name() + "]To[" + toStatus + "]");
  }

  // 条件
  public Condition<Order> submitCondition() {
    return order -> true;
  }

  // 条件
  public Condition<Order> sealSucceedCondition() {
    return order -> OrderStatus.WAIT_SIGN_SEAL.equals(order.getStatus());
  }
}