package com.bird.zookeeper.exception;

/**
 * @author youly
 * 2019/10/21 15:20
 */
public class ZKException extends RuntimeException {
    public ZKException(String message) {
        super(message);
    }

    public ZKException(String message, Throwable cause) {
        super(message, cause);
    }
}
