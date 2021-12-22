package cn.jho.springcloud.controller;

import cn.jho.springcloud.entities.CommonResult;
import cn.jho.springcloud.entities.Payment;
import cn.jho.springcloud.entities.enums.ReturnCode;
import cn.jho.springcloud.exceptions.GlobalRuntimeException;
import cn.jho.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-12-06 23:04
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    public static final String SERVICE_ID = "CLOUD-PAYMENT-SERVICE";

    private final RestTemplate restTemplate;

    private final RestTemplate restTemplateWithLB;

    private final LoadBalancer loadBalancer;

    private final DiscoveryClient discoveryClient;

    public OrderController(RestTemplate restTemplate, RestTemplate restTemplateWithLB,
                           DiscoveryClient discoveryClient, LoadBalancer loadBalancer) {
        this.restTemplate = restTemplate;
        this.restTemplateWithLB = restTemplateWithLB;
        this.discoveryClient = discoveryClient;
        this.loadBalancer = loadBalancer;
    }

    @PostMapping("/payment")
    public CommonResult<Payment> createPayment(Payment payment) {
        return restTemplateWithLB.postForObject(PAYMENT_URL + "/payment", payment, CommonResult.class);
    }

    @GetMapping("/payment/{paymentId}")
    public CommonResult<Payment> getPaymentById(@PathVariable Long paymentId) {
        return restTemplateWithLB.getForObject(PAYMENT_URL + "/payment/" + paymentId, CommonResult.class);
    }

    @GetMapping("/payment/getForEntity/{paymentId}")
    public CommonResult<Payment> getPaymentEntityById(@PathVariable Long paymentId) {
        ResponseEntity<CommonResult> entity = restTemplateWithLB.getForEntity(PAYMENT_URL + "/payment/" + paymentId, CommonResult.class);
        log.info("header={}", entity.getHeaders());
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        } else {
            return new CommonResult<>(ReturnCode.RC400.getCode(), ReturnCode.RC400.getMessage());
        }
    }

    @GetMapping("/payment/lb")
    public CommonResult<String> getPaymentLb() {
        List<ServiceInstance> instances = discoveryClient.getInstances(SERVICE_ID);
        if (instances == null || instances.isEmpty()) {
            throw new GlobalRuntimeException("找不到payment服务");
        }
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();
        log.info("uri={}", uri);
        return restTemplate.getForObject(serviceInstance.getUri() + "/payment/lb", CommonResult.class);
    }

    @GetMapping("/payment/zipkin")
    public CommonResult paymentZipkin() {
        return restTemplateWithLB.getForObject(PAYMENT_URL + "/payment/zipkin", CommonResult.class);
    }

}
