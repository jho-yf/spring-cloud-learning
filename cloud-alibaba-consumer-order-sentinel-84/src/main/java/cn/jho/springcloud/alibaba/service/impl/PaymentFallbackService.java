package cn.jho.springcloud.alibaba.service.impl;

import cn.jho.springcloud.alibaba.feign.PaymentFeignClient;
import cn.jho.springcloud.entities.CommonResult;
import cn.jho.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

/**
 * 服务降级处理
 *
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-11 23:01
 */
@Component
public class PaymentFallbackService implements PaymentFeignClient {

    @Override
    public CommonResult<Payment> getPaymentById(Long id) {
        return new CommonResult<>(400, "【PaymentFallbackService.getPaymentById】:id=" + id);
    }

}
