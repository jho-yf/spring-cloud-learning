package cn.jho.springcloud.service.impl;

import cn.jho.springcloud.entities.Payment;
import cn.jho.springcloud.mapper.PaymentMapper;
import cn.jho.springcloud.service.PaymentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-12-05 8:06
 */
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements PaymentService {
}
