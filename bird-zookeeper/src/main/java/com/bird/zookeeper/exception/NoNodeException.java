package com.bird.zookeeper.exception;

/**
 * @author youly
 * 2019/10/21 14:56
 */
public class NoNodeException extends Exception {

    public NoNodeException(String message) {
        super(message);
    }

    public NoNodeException(String message, Throwable cause) {
        super(message, cause);
    }
}
