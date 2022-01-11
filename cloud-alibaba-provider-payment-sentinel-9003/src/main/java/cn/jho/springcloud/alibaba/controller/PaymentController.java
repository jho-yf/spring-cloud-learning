package cn.jho.springcloud.alibaba.controller;

import cn.jho.springcloud.entities.CommonResult;
import cn.jho.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-11 20:02
 */
@RestController
@RequestMapping("/sentinel/payment")
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    public static final Map<Long, Payment> DATASOURCE = new HashMap<>();

    static {
        DATASOURCE.put(1L, new Payment(1L, "654ef8c3-72c9-11ec-89ed-5600a887d01e"));
        DATASOURCE.put(2L, new Payment(2L, "43b9682b-72c9-11ec-89ed-5600a887d01e"));
        DATASOURCE.put(3L, new Payment(1L, "c3fd0f12-72c8-11ec-89ed-5600a887d01e"));
    }

    @GetMapping("/{id}")
    public CommonResult<Payment> getById(@PathVariable("id") long id) {
        return new CommonResult<>(200,
                "【getById】serverPort=" + serverPort,
                DATASOURCE.get(id));
    }

}
