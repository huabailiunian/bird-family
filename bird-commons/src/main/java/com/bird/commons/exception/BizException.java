package com.bird.commons.exception;

/**
 * 业务异常，可预防、可避免、可处理型异常
 *
 * @author master
 * @date 2020-04-23 10:37
 */
public class BizException extends Exception {

    private int code;

    public BizException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
