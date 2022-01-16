package cn.jho.springcloud.seata.controller;

import cn.jho.springcloud.entities.CommonResult;
import cn.jho.springcloud.seata.entity.Order;
import cn.jho.springcloud.seata.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-17 7:35
 */
@RestController
@RequestMapping("/seata/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public CommonResult create(Order order) {
        orderService.create(order);
        return new CommonResult(200, "订单创建成功");
    }

}
