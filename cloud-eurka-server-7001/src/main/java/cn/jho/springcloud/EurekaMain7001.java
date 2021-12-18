package cn.jho.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-12-07 9:12
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaMain7001 {

    /*
     *  @EnableEurekaServer：启动Eureka服务
     */

    public static void main(String[] args) {
        SpringApplication.run(EurekaMain7001.class, args);
    }

}
