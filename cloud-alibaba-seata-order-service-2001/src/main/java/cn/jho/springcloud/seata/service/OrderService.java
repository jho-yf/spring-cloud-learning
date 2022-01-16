package cn.jho.springcloud.seata.service;

import cn.jho.springcloud.seata.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-16 9:10
 */
public interface OrderService extends IService<Order> {

    /**
     * 新建订单
     * @param order {@link Order}
     */
    void create(Order order);

}
