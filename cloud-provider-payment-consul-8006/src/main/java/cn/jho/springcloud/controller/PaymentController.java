package cn.jho.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-12-09 22:56
 */
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment")
    public String payment() {
        return "springCloud with consul:" + serverPort + "\t" + UUID.randomUUID();
    }

}
