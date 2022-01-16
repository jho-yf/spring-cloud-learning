package cn.jho.springcloud.seata.mapper;


import cn.jho.springcloud.seata.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-16 9:06
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {


}
