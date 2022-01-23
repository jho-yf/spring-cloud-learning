package cn.jho.springcloud.seata.mapper;

import cn.jho.springcloud.seata.entity.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-23 9:26
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {

    /**
     * 扣减账户余额
     *
     * @param userId 用户id
     * @param money 扣减金额
     */
    void decrease(@Param("user_id") Long userId, @Param("money") BigDecimal money);

}
