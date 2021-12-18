package cn.jho.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-12-11 10:37
 */
public interface LoadBalancer {

    /**
     * 获取服务实例
     *
     * @param serviceInstances {@link List<ServiceInstance>}
     * @return {@link ServiceInstance}
     */
    ServiceInstance instances(List<ServiceInstance> serviceInstances);

}
