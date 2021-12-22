package cn.jho.springcloud.controller;

import cn.jho.springcloud.entities.CommonResult;
import cn.jho.springcloud.entities.Payment;
import cn.jho.springcloud.exceptions.GlobalRuntimeException;
import cn.jho.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-12-05 8:09
 */
@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    private final PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Value("${spring.application.name}")
    private String applicationName;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * 创建支付
     * @return {@link CommonResult<Payment>}
     */
    @PostMapping
    public Payment createPayment(@Valid @RequestBody Payment payment) {
        boolean isSave = paymentService.save(payment);
        if (!isSave) {
            log.info("订单插入数据库失败");
            throw new GlobalRuntimeException("订单插入数据库失败");
        }
        log.info("[{}]插入订单成功", serverPort);
        return payment;
    }

    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable String id) {
        Payment payment = paymentService.getById(id);
        if (payment == null) {
            throw new GlobalRuntimeException("找不到此订单");
        }
        log.info("[{}]获取订单[{}]", serverPort, id);
        return paymentService.getById(id);
    }

    @GetMapping("/lb")
    public String getPaymentLB() {
        return applicationName + ":" + serverPort;
    }

    @GetMapping("/timeout")
    public String getPaymentTimeOut() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return applicationName + ":" + serverPort;
    }

    @GetMapping("/zipkin")
    public String paymentZipkin() {
        return "paymentZipkin[" + serverPort + "]";
    }

}
