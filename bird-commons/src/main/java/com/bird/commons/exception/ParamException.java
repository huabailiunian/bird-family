package com.bird.commons.exception;


/**
 * 参数异常
 *
 * @author master
 */
public class ParamException extends RuntimeException {

    /**
     * 错误码
     */
    private int code;

    public ParamException() {
    }

    public ParamException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
