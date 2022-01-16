package cn.jho.springcloud.seata.service.feign;

import cn.jho.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-16 23:28
 */
@FeignClient(value = "cloud-alibaba-seata-storage-service")
public interface StorageService {

    /**
     * 减少产品库存
     * @param productId 产品id
     * @param count 减少数量
     * @return {@link CommonResult}
     */
    @PostMapping(value = "/storage/decrease")
    CommonResult decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);

}
