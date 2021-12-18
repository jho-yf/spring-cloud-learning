package cn.jho.springcloud.controller;

import cn.jho.springcloud.entities.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-12-09 20:49
 */
@RestController
@RequestMapping("/order")
public class OrderZkController {

    public static final String INVOKE_URL = "http://cloud-provider-payment";

    private final RestTemplate restTemplate;

    public OrderZkController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/payment")
    public String paymentInfo() {
        CommonResult result = restTemplate.getForObject(INVOKE_URL + "/payment", CommonResult.class);
        return (String) result.getData();
    }

}
