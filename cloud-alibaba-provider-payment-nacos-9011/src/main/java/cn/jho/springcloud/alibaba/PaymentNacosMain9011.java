package cn.jho.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-12-25 10:44
 */
@EnableDiscoveryClient
@SpringBootApplication
public class PaymentNacosMain9011 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentNacosMain9011.class, args);
    }

}
