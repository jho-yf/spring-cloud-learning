package cn.jho.springcloud.seata.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-23 9:22
 */
@Data
public class Account {

    private Long id;

    /** 用户id */
    private Long userId;

    /** 总额度 */
    private BigDecimal total;

    /** 已用额度 */
    private BigDecimal used;

    /** 剩余额度 */
    private BigDecimal residue;

}
