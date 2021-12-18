package cn.jho.springcloud.entities;

import cn.jho.springcloud.entities.enums.ReturnCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用结果实体
 *
 * @author JHO xu-jihong@qq.com
 * @date 2021-12-05 7:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    /**
     * 结果状态，具体状态码见{@link ReturnCode}
     */
    private Integer code;

    private String message;

    private T data;

    public CommonResult(Integer code, String message) {
        this(code, message, null);
    }

    public static <T> CommonResult<T> success(T data) {
        CommonResult<T> commonResult = new CommonResult<>();
        commonResult.setCode(ReturnCode.RC200.getCode());
        commonResult.setMessage(ReturnCode.RC200.getMessage());
        commonResult.setData(data);
        return commonResult;
    }

    public static <T> CommonResult<T> fail(int code, String message) {
        CommonResult<T> commonResult = new CommonResult<>();
        commonResult.setCode(code);
        commonResult.setMessage(message);
        return commonResult;
    }

}
