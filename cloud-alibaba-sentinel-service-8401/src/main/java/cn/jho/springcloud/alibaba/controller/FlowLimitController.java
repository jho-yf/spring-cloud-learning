package cn.jho.springcloud.alibaba.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-07 8:04
 */
@RestController
@RequestMapping("/sentinel/flow-limit")
public class FlowLimitController {

    @GetMapping("/test1")
    public String test1() {
        return "------------test1";
    }

    @GetMapping("/test2")
    public String test2() {
        return "------------test2";
    }

}
