package com.bird.commons.exception;

/**
 * 服务异常
 *
 * @author master
 * @date 2020-04-23 10:38
 */
public class ServiceException extends RuntimeException {

    private int code;

    public ServiceException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
