package cn.jho.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-12-08 7:03
 */
@Slf4j
@RestController
@RequestMapping("/discovery")
public class DiscoveryController {

    private final DiscoveryClient discoveryClient;

    public DiscoveryController(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @GetMapping
    public DiscoveryClient discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("服务名称：{}", service);
            List<ServiceInstance> instances = discoveryClient.getInstances(service);
            for (ServiceInstance instance : instances) {
                log.info(instance.getServiceId() + "\t"
                        + instance.getInstanceId() + "\t"
                        + instance.getHost() + "\t"
                        + instance.getPort() + "\t"
                        + instance.getUri());
            }
        }
        return this.discoveryClient;
    }

}
