package cn.jho.springcloud.alibaba.controller;

import cn.jho.springcloud.alibaba.feign.PaymentFeignClient;
import cn.jho.springcloud.entities.CommonResult;
import cn.jho.springcloud.entities.Payment;
import cn.jho.springcloud.exceptions.GlobalRuntimeException;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-11 20:25
 */
@RestController
@RequestMapping("/sentinel/order")
@Slf4j
public class CircleBreakerController {

    @Value("${payment-service.nacos-user-service}")
    private String paymentServiceUrl;

    private final RestTemplate restTemplate;

    private final PaymentFeignClient paymentFeignClient;

    public CircleBreakerController(RestTemplate restTemplate, PaymentFeignClient paymentFeignClient) {
        this.restTemplate = restTemplate;
        this.paymentFeignClient = paymentFeignClient;

    }

    @GetMapping("/fallback/{id}")
    @SentinelResource(value = "fallback",
            fallback = "handleFallback",
            blockHandler = "handleBlockHandler", exceptionsToIgnore = {IllegalArgumentException.class})
    public CommonResult<Payment> fallback(@PathVariable("id") Long id) {
        if (id == 4) {
            throw new IllegalArgumentException("参数非法");
        }

        CommonResult result = restTemplate.getForObject(paymentServiceUrl + "/sentinel/payment/" + id, CommonResult.class);

        if (result.getData() == null) {
            throw new GlobalRuntimeException("没有id为" + id + "的记录");
        }

        return result;
    }

    public CommonResult<Payment> handleFallback(Long id, Throwable e) {
        return new CommonResult<>(400, "【handleFallback】id=" + id + ",exception=" + e);
    }

    public CommonResult<Payment> handleBlockHandler(Long id, BlockException e) {
        return new CommonResult<>(400, "【handleBlockHandler】id=" + id + ",exception=" + e);
    }

    @GetMapping("/feign/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return paymentFeignClient.getPaymentById(id);
    }

}
