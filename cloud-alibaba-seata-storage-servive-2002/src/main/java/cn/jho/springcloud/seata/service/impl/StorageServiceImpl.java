package cn.jho.springcloud.seata.service.impl;

import cn.jho.springcloud.seata.entity.Storage;
import cn.jho.springcloud.seata.mapper.StorageMapper;
import cn.jho.springcloud.seata.service.StorageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-23 9:07
 */
@Service
public class StorageServiceImpl extends ServiceImpl<StorageMapper, Storage> implements StorageService {

    private final StorageMapper storageMapper;

    public StorageServiceImpl(StorageMapper storageMapper) {
        this.storageMapper = storageMapper;
    }

    @Override
    public void decrease(Long productId, Integer count) {
        this.storageMapper.decrease(productId, count);
    }
}
