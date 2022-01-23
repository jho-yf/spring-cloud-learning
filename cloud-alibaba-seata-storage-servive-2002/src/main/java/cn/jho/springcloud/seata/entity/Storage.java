package cn.jho.springcloud.seata.entity;

import lombok.Data;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-23 8:48
 */
@Data
public class Storage {

    private Long id;

    /** 产品id */
    private Long productId;

    /** 总库存 */
    private Integer total;

    /** 已用库存 */
    private Integer used;

    /** 剩余库存 */
    private Integer residue;

}
