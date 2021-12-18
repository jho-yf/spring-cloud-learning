package cn.jho.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.jho.springcloud.exceptions.GlobalRuntimeException;
import cn.jho.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-12-12 7:28
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public String paymentInfoOk(Integer id) {
        return "[paymentInfoOk][id: " + id + "]-线程池: " + Thread.currentThread().getName();
    }

    @Override
    @HystrixCommand(fallbackMethod = "paymentInfoTimeoutFallbackHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String paymentInfoTimeout(Integer id) {
        /*
         * @HystrixCommand 设置方法超时时间调用峰值，超过则运行兜底处理方法，做服务降级fallback
         */
        if (id == 0) {
            throw new GlobalRuntimeException("系统异常");
        }
        try {
            TimeUnit.SECONDS.sleep(id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "[paymentInfoOk][id: " + id + "][timeout: " + id + "]-线程池: " + Thread.currentThread().getName();
    }

    /**
     * fallbaclk 兜底处理
     *
     * @param id {@link Integer}
     * @return {@link String}
     */
    public String paymentInfoTimeoutFallbackHandler(Integer id) {
        String msg = "系统繁忙";
        if (id == 0) {
            msg = "系统错误";
        }
        return "[paymentInfoTimeoutFallbackHandler][id: " + id
                + "]-线程池: " + Thread.currentThread().getName() + " 【" + msg + "】";
    }

    // 以上===================服务降级
    // 以下===================服务熔断

    @Override
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallBack", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),                          // 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),             // 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),       // 时间查看器
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),           // 失败率达到多少后熔断
    })
    public String paymentCircuitBreaker(Integer id) {
        if (id < 0) {
            throw new GlobalRuntimeException("id不能为负数");
        }
        String serialNum = IdUtil.simpleUUID();
        return "[paymentCircuitBreaker][id: " + id + "][serialNum: " + serialNum + "]-线程池: " + Thread.currentThread().getName();
    }

    public String paymentCircuitBreakerFallBack(Integer id) {
        return "[paymentCircuitBreakerFallBack][id: " + id + "]-线程池: " + Thread.currentThread().getName();
    }

}
