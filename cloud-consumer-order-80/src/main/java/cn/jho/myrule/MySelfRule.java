package cn.jho.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-12-11 7:28
 */
@Configuration
public class MySelfRule {

    /*
     * 此自定义配置不能放在@ComponentScan所扫描的包下以及子包下
     * 否则自定义的配置类就会被所有Ribbon客户端共享，达不到特殊化定制的目的
     */

    @Bean
    public IRule myRule() {
        // 定义为随机
        return new RandomRule();
    }

}
