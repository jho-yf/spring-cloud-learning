package cn.jho.springcloud.seata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-23 8:44
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class SeataStorageMainApp2002 {

    public static void main(String[] args) {
        SpringApplication.run(SeataStorageMainApp2002.class, args);
    }

}
