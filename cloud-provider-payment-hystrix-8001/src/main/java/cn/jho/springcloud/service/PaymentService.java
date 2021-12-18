package cn.jho.springcloud.service;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-12-12 7:27
 */
public interface PaymentService {

    /**
     * 正常返回 - 测试服务降级
     *
     * @param id {@link Integer}
     * @return paymentInfoOk
     */
    String paymentInfoOk(Integer id);

    /**
     * 超时异常 - 测试服务降级
     *
     * @param id {@link Integer}
     * @return paymentInfoTimeout
     */
    String paymentInfoTimeout(Integer id);


    /**
     * 服务熔断
     *
     * @param id {@link Integer}
     * @return paymentCircuitBreaker
     */
    String paymentCircuitBreaker(Integer id);

}
