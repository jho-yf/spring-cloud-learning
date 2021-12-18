package cn.jho.springcloud.controller;

import cn.jho.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-12-12 7:34
 */
@RestController
@RequestMapping("/hystrix/payment")
@Slf4j
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/ok/{paymentId}")
    public String paymentInfoOk(@PathVariable Integer paymentId) {
        String result = paymentService.paymentInfoOk(paymentId);
        log.info("result={}", result);
        return result;
    }

    @GetMapping("/timeout/{paymentId}")
    public String paymentInfoTimeout(@PathVariable Integer paymentId) {
        String result = paymentService.paymentInfoTimeout(paymentId);
        log.info("result={}", result);
        return result;
    }

    @GetMapping("/circuit/{paymentId}")
    public String paymentCircuitBreaker(@PathVariable Integer paymentId) {
        String result = paymentService.paymentCircuitBreaker(paymentId);
        log.info("result={}", result);
        return result;
    }

}
