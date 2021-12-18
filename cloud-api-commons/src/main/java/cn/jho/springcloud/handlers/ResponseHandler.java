package cn.jho.springcloud.handlers;

import cn.jho.springcloud.entities.CommonResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-12-05 23:17
 */
@RestControllerAdvice
public class ResponseHandler implements ResponseBodyAdvice<Object> {

    /*
     * https://mp.weixin.qq.com/s/wLLATZqArx9Sl9xX8G0irQ
     * @RestControllerAdvice是@RestController注解的增强，可以实现三个方面的功能：
     *      1. 全局异常处理
     *      2. 全局数据绑定
     *      3. 全局数据预处理
     *
     * ResponseBodyAdvice的作用：拦截Controller方法的返回值，统一处理返回值/响应体，一般用来统一返回格式，加解密，签名等等。
     */

    private final ObjectMapper objectMapper;

    public ResponseHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * 是否支持advice功能
     *
     * @param methodParameter 方法参数
     * @param aClass {@link Class<? extends HttpMessageConverter<?>>}
     * @return true 支持，false 不支持
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        // 如果类上标识@IgnoreResponseAdvice方法则不拦截
        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }
        // 如果类上标识@IgnoreResponseAdvice方法则不拦截
        Method method = methodParameter.getMethod();
        return method == null || !method.isAnnotationPresent(IgnoreResponseAdvice.class);
    }

    /**
     * 对返回的数据进行处理
     */
    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (null == o) {
            return CommonResult.success("");
        }

        if (o instanceof String) {
            return objectMapper.writeValueAsString(CommonResult.success(o));
        }

        if (o instanceof CommonResult) {
            return o;
        }
        return CommonResult.success(o);
    }

}
