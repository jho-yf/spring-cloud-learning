package cn.jho.springcloud.exceptions;

/**
 * @author JHO xu-jihong@qq.com
 * @date 2021-12-06 20:19
 */
public class GlobalRuntimeException extends RuntimeException {

    public GlobalRuntimeException() {

    }

    public GlobalRuntimeException(String message) {
        super(message);
    }

}
