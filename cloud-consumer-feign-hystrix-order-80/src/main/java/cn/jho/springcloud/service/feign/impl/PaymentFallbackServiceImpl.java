package cn.jho.springcloud.service.feign.impl;

import cn.jho.springcloud.service.feign.PaymentFeignService;
import org.springframework.stereotype.Service;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-12-14 7:36
 */
@Service
public class PaymentFallbackServiceImpl implements PaymentFeignService {

    @Override
    public String paymentInfoOk(Integer paymentId) {
        return "[PaymentFallbackServiceImpl][paymentInfoOk][id: " + paymentId
                + "]-线程池: " + Thread.currentThread().getName();
    }

    @Override
    public String paymentInfoTimeout(Integer paymentId) {
        return "[PaymentFallbackServiceImpl][paymentInfoTimeout][id: " + paymentId
                + "]-线程池: " + Thread.currentThread().getName();
    }
}
