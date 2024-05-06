package cn.xwlin.controller;

import cn.xwlin.dto.GetUserInfoRequest;
import cn.xwlin.logger.LoggerUtils;
import cn.xwlin.service.HelloService;
import cn.xwlin.service.OrderService;
import cn.xwlin.vo.UserFullInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("getNewOrderId")
    public Long getNewOrderId() {
        return orderService.getOrderId();
    }

}
