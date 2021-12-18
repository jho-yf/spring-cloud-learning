package cn.jho.springcloud.mapper;

import cn.jho.springcloud.entities.Payment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 支付Mapper
 *
 * @author JHO xu-jihong@qq.com
 * @date 2021-12-05 7:45
 */
@Mapper
public interface PaymentMapper extends BaseMapper<Payment> {

}
