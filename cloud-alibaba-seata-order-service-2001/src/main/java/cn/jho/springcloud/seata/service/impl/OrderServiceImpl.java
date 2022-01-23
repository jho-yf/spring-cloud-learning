package cn.jho.springcloud.seata.service.impl;

import cn.jho.springcloud.seata.entity.Order;
import cn.jho.springcloud.seata.mapper.OrderMapper;
import cn.jho.springcloud.seata.service.OrderService;
import cn.jho.springcloud.seata.service.feign.AccountService;
import cn.jho.springcloud.seata.service.feign.StorageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-16 9:11
 */
@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    private final StorageService storageService;

    private final AccountService accountService;

    public OrderServiceImpl(StorageService storageService, AccountService accountService) {
        this.storageService = storageService;
        this.accountService = accountService;
    }

    /**
     * 创建订单 - 调用库存服务扣减库存 - 调用账户服务扣减账户余额 - 修改订单状态
     *
     * @param order {@link Order}
     */
    @GlobalTransactional(name = "demo-create-order", rollbackFor = Exception.class)
    @Override
    public void create(Order order) {
        log.info("【开始】新建订单...");
        order.setStatus(0);
        this.save(order);
        log.info("【结束】新建订单...");

        log.info("【开始】订单微服务调用库存微服务，减少产品[{}]库存[{}]...", order.getProductId(), order.getCount());
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("【结束】订单微服务调用库存微服务，减少产品[{}]库存[{}]...", order.getProductId(), order.getCount());

        log.info("【开始】订单微服务调用账户微服务，从账号[{}]扣钱[{}]...", order.getUserId(), order.getMoney());
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("【结束】订单微服务调用账户微服务，从账号[{}]扣钱[{}]...", order.getUserId(), order.getMoney());

        log.info("【开始】修改订单状态...");
        order.setStatus(1);
        this.updateById(order);
        log.info("【结束】修改订单状态...");
    }

}
