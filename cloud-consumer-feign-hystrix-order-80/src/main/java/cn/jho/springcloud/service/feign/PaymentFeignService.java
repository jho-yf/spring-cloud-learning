package cn.jho.springcloud.service.feign;

import cn.jho.springcloud.service.feign.impl.PaymentFallbackServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-12-13 8:00
 */
@FeignClient(value = "CLOUD-PROVIDER-PAYMENT-HYSTRIX", fallback = PaymentFallbackServiceImpl.class)
public interface PaymentFeignService {

    /**
     * paymentInfoOk
     *
     * @param paymentId Integer
     * @return String
     */
    @GetMapping("/hystrix/payment/ok/{paymentId}")
    String paymentInfoOk(@PathVariable(value = "paymentId") Integer paymentId);

    /**
     * paymentInfoTimeout
     *
     * @param paymentId Integer
     * @return String
     */
    @GetMapping("/hystrix/payment/timeout/{paymentId}")
    String paymentInfoTimeout(@PathVariable(value = "paymentId") Integer paymentId);

}
