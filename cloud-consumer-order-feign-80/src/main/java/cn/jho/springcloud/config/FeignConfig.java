package cn.jho.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-12-11 15:16
 */
@Configuration
public class FeignConfig {

    @Bean
    Logger.Level feignLoggerLevel() {
        /*
         * 日志级别：
         *          NONE    默认，不显示日志
         *          BASIC   仅记录请求方法、URL、响应状态码、执行时间
         *          HEADERS 在BASIC基础上，记录请求和响应头信息
         *          FULL    在HEADERS基础上，记录请求和响应的正文和元数据
         */
        return Logger.Level.FULL;
    }

}
