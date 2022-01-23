package cn.jho.springcloud.seata.service;

import cn.jho.springcloud.seata.entity.Account;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-23 9:32
 */
public interface AccountService extends IService<Account> {

    /**
     * 扣减账户金额
     *
     * @param userId 用户id
     * @param money 扣减金额
     */
    void decrease(Long userId, BigDecimal money);

}
