package cn.jho.springcloud.alibaba.feign;

import cn.jho.springcloud.alibaba.service.impl.PaymentFallbackService;
import cn.jho.springcloud.entities.CommonResult;
import cn.jho.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-11 22:55
 */
@FeignClient(value = "${payment-service.nacos-user-service}/sentinel/payment", fallback = PaymentFallbackService.class)
public interface PaymentFeignClient {

    /**
     * 根据id获取{@link Payment}
     *
     * @param id id
     * @return {@link Payment}
     */
    @GetMapping("/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);
    
}
