package cn.jho.springcloud.alibaba.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-12-25 11:18
 */
@RestController
@RequestMapping("/nacos/order")
@Slf4j
public class OrderController {

    @Value("${server.port}")
    private String serverPort;

    @Value("${service-url.nacos-payment-service}")
    private String serverUrl;

    private final RestTemplate restTemplate;

    public OrderController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/payment/{paymentId}")
    public String getPayment(@PathVariable String paymentId) {
        return restTemplate.getForObject(serverUrl + "/nacos/payment/" + paymentId, String.class);
    }

}
