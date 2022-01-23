package cn.jho.springcloud.seata.service.impl;

import cn.jho.springcloud.seata.entity.Account;
import cn.jho.springcloud.seata.mapper.AccountMapper;
import cn.jho.springcloud.seata.service.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-23 9:34
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    private final AccountMapper accountMapper;

    public AccountServiceImpl(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    public void decrease(Long userId, BigDecimal money) {
        // 模拟超时异常
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        accountMapper.decrease(userId, money);
    }

}
