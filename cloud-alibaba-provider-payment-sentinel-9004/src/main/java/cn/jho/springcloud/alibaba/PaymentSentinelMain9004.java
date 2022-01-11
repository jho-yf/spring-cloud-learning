package cn.jho.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-11 19:59
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentSentinelMain9004 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentSentinelMain9004.class, args);
    }

}
