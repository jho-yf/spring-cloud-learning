package cn.jho.springcloud.seata.service.feign;

import cn.jho.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-16 23:42
 */
@FeignClient("cloud-alibaba-seata-account-service")
public interface AccountService {

    /**
     * 账号扣钱
     *
     * @param userId 用户id
     * @param money 金额
     * @return {@link CommonResult}
     */
    @PostMapping(value = "/account/decrease")
    CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);

}
