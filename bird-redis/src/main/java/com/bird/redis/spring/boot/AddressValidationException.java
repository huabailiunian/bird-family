package com.bird.redis.spring.boot;

/**
 * 校验异常类
 *
 * @author youly
 * 2019/1/7 19:26
 */
public class AddressValidationException extends RuntimeException {

    public AddressValidationException(String message) {
        super(message);
    }
}
