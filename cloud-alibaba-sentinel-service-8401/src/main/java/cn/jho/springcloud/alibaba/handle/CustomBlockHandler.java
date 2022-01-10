package cn.jho.springcloud.alibaba.handle;

import cn.jho.springcloud.entities.CommonResult;
import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2022-01-11 7:30
 */
public class CustomBlockHandler {

    public static CommonResult handlerException1(BlockException exception) {
        return new CommonResult(444,
                "【handlerException1】全局异常提示:" + exception.getClass().getCanonicalName());
    }

    public static CommonResult handlerException2(BlockException exception) {
        return new CommonResult(444,
                "【handlerException2】全局异常提示:" + exception.getClass().getCanonicalName());
    }

}
