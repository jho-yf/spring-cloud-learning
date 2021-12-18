package cn.jho.springcloud.controller;

import cn.jho.springcloud.entities.CommonResult;
import cn.jho.springcloud.entities.Payment;
import cn.jho.springcloud.service.feign.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-12-11 12:04
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    private final PaymentFeignService paymentFeignService;

    public OrderController(PaymentFeignService paymentFeignService) {
        this.paymentFeignService = paymentFeignService;
    }

    @GetMapping("/payment/{paymentId}")
    public CommonResult<Payment> getPaymentById(@PathVariable Long paymentId) {
        return paymentFeignService.getPaymentById(paymentId);
    }

    @GetMapping("/payment/timeout")
    public String paymentFeignTimeout() {
        return paymentFeignService.getPaymentTimeOut();
    }

}
