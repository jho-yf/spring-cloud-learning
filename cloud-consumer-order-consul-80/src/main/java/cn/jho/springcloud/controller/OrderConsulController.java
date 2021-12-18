package cn.jho.springcloud.controller;

import cn.jho.springcloud.entities.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-12-09 23:12
 */
@RestController
public class OrderConsulController {

    public static final String INVOKE_URL = "http://consul-provider-payment";

    private final RestTemplate restTemplate;

    public OrderConsulController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/consul/order/payment")
    public String payment() {
        CommonResult result = restTemplate.getForObject(INVOKE_URL + "/payment", CommonResult.class);
        return (String) result.getData();
    }

}
