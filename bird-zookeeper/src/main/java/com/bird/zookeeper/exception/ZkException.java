package com.bird.zookeeper.exception;

/**
 * @author youly
 * 2019/10/21 15:20
 */
public class ZkException extends RuntimeException {
    public ZkException(String message) {
        super(message);
    }

    public ZkException(String message, Throwable cause) {
        super(message, cause);
    }
}
