package cn.jho.springcloud.controller;

import cn.jho.springcloud.exceptions.GlobalRuntimeException;
import cn.jho.springcloud.service.feign.PaymentFeignService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-12-13 19:29
 */
@RestController
@RequestMapping("/hystrix/order")
@DefaultProperties(defaultFallback = "globalFallback")
public class OrderHystrixController {

    private final PaymentFeignService paymentFeignService;

    public OrderHystrixController(PaymentFeignService paymentFeignService) {
        this.paymentFeignService = paymentFeignService;
    }

    @HystrixCommand
    @GetMapping("/payment/ok/{paymentId}")
    public String paymentInfoOk(@PathVariable Integer paymentId) {
        if (paymentId < 0) {
            throw new GlobalRuntimeException("订单系统异常！");
        }
        return paymentFeignService.paymentInfoOk(paymentId);
    }

    @GetMapping("/payment/timeout/{paymentId}")
    @HystrixCommand(fallbackMethod = "paymentInfoTimeoutFallbackHandler", commandProperties = {
            // com.netflix.hystrix.HystrixCommandProperties
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfoTimeout(@PathVariable Integer paymentId) {
        /*
         * @HystrixCommand 设置方法超时时间调用峰值，超过则运行兜底处理方法，做服务降级fallback
         */
        if (paymentId < 0) {
            throw new GlobalRuntimeException("订单系统异常！");
        }
        return paymentFeignService.paymentInfoTimeout(paymentId);
    }

    /**
     * fallback 兜底处理
     *
     * @param id {@link Integer}
     * @return {@link String}
     */
    public String paymentInfoTimeoutFallbackHandler(Integer id) {
        String msg = "被调用系统繁忙";
        if (id < 0) {
            msg = "订单系统异常！";
        }
        return "[paymentInfoTimeoutFallbackHandler][id: " + id
                + "]-线程池: " + Thread.currentThread().getName() + " 【" + msg + "】";
    }

    /**
     * 全局fallback处理
     *
     * @return {@link String}
     */
    public String globalFallback() {
        return "[globalFallback]"
                + "]-线程池: " + Thread.currentThread().getName() + " 【全局异常】";
    }

    // 以上===================服务降级
    // 以下===================服务熔断


}
