package cn.xwlin.controller;

import cn.xwlin.entity.OrderInfo;
import cn.xwlin.mapper.OrderInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("order")
public class OrderInfoController {

  @Autowired
  private OrderInfoMapper orderInfoMapper;

  @RequestMapping("new")
  public void newOrder(@RequestBody OrderInfo orderInfo) {
    orderInfo.setCrtTime(new Date());
    orderInfoMapper.insert(orderInfo);
  }

  @RequestMapping("findByUserId")
  public List<OrderInfo> findByUserId(Integer userId) {
    return orderInfoMapper.listOrder(userId);
  }

  @RequestMapping("findById")
  public OrderInfo findById(Long id) {
    return orderInfoMapper.selectByPrimaryKey(id);
  }

  @PostMapping("findTestAll")
  public List<Integer> findTestAll(){
    return orderInfoMapper.findTestAll();
  }
}
