package cn.jho.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-07 8:03
 */
@EnableDiscoveryClient
@SpringBootApplication
public class SentinelServiceMain8401 {

    public static void main(String[] args) {
        SpringApplication.run(SentinelServiceMain8401.class, args);
    }

}
