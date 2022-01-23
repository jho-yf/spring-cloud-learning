package cn.jho.springcloud.seata.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-15 17:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_order")
public class Order {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long userId;

    private Long productId;

    private Integer count;

    private BigDecimal money;

    /**
     * 订单状态：
     *  0 创建中
     *  1 已完结
     */
    private Integer status;

}
