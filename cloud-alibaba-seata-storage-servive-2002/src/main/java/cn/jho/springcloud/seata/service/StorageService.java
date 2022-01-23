package cn.jho.springcloud.seata.service;

import cn.jho.springcloud.seata.entity.Storage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-23 9:06
 */
public interface StorageService extends IService<Storage> {

    /**
     * 扣减库存
     *
     * @param productId 产品id
     * @param count 扣减数量
     */
    void decrease(Long productId, Integer count);


}
