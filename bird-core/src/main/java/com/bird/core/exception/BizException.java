package com.bird.core.exception;


/**
 * 业务异常
 *
 * @author master
 */
public class BizException extends RuntimeException {

    /**
     * 错误码
     */
    private int code;

    public BizException() {
    }

    public BizException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
