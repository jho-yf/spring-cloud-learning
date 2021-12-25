package cn.jho.springcloud.alibaba.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-12-25 10:46
 */
@RestController
@RequestMapping("/nacos/payment")
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/{paymentId}")
    public String getPayment(@PathVariable String paymentId) {
        return "nacos[" + serverPort + "][paymentId: " + paymentId + "] - 线程池: " + Thread.currentThread().getName();
    }

}
