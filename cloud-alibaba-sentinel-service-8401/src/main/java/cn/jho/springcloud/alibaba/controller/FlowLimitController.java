package cn.jho.springcloud.alibaba.controller;

import cn.jho.springcloud.alibaba.handle.CustomBlockHandler;
import cn.jho.springcloud.entities.CommonResult;
import cn.jho.springcloud.entities.Payment;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-07 8:04
 */
@RestController
@RequestMapping("/sentinel/flow-limit")
@Slf4j
public class FlowLimitController {

    @GetMapping("/test1")
    public String test1() {
        return "------------test1";
    }

    @GetMapping("/test2")
    public String test2() {
        return "------------test2";
    }

    /**
     * 热点key测试
     */
    @GetMapping("/hot")
    @SentinelResource(value = "hot", blockHandler = "dealHostKey")
    public String hotKey(@RequestParam(value = "name", required = false) String name,
                         @RequestParam(value = "age", required = false) Integer age) {
        log.info("name={},age={}", name, age);
        return "【hotKey】name=" + name + ",age=" + age;
    }

    /**
     * 兜底处理
     */
    public String dealHostKey(String name, Integer age, BlockException exception) {
        return "【blockHandler】name=" + name + ",age=" + age + ",exception=" + exception;
    }

    @GetMapping("/resource")
    @SentinelResource(value = "resource", blockHandler = "handleException")
    public CommonResult<Payment> byResource() {
        return new CommonResult<>(200, "【byResource】按资源名称限流测试OK",
                new Payment(166L, "serial166"));
    }

    public CommonResult<Payment> handleException(BlockException exception) {
        return new CommonResult<>(444,
                "【CommonResult】资源不可用\t" + exception.getClass().getCanonicalName());
    }

    @GetMapping("/url")
    @SentinelResource(value = "url")
    public CommonResult<Payment> byUrl() {
        return new CommonResult<>(200, "【byUrl】按url限流测试OK",
                new Payment(166L, "serial166"));
    }

    @GetMapping("/global-block-handler")
    @SentinelResource(value = "global-block-handler",
            blockHandlerClass = CustomBlockHandler.class,
            blockHandler = "handlerException1")
    public CommonResult<Payment> testGlobalBlockHandler() {
        return new CommonResult<>(200, "【testGlobalBlockHandler】测试全局自定义异常OK",
                new Payment(166L, "serial166"));
    }

}
