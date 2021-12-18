package cn.jho.springcloud.service.feign;

import cn.jho.springcloud.entities.CommonResult;
import cn.jho.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-12-11 11:55
 */
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    /**
     * 根据id获取{@link Payment}
     *
     * @param id id
     * @return {@link Payment}
     */
    @GetMapping("/payment/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    /**
     * 获取{@link Payment}
     *
     * @return {@link String}
     */
    @GetMapping("/payment/timeout")
    String getPaymentTimeOut();

}
