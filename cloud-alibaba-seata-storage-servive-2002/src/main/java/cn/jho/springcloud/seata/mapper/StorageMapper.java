package cn.jho.springcloud.seata.mapper;

import cn.jho.springcloud.seata.entity.Storage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-23 8:54
 */
@Mapper
public interface StorageMapper extends BaseMapper<Storage> {

    /**
     * 扣减库存
     *
     * @param productId 产品id
     * @param count 扣减数量
     */
    void decrease(@Param("productId") Long productId, @Param("count") Integer count);

}
