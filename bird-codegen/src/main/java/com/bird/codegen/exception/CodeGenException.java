package com.bird.codegen.exception;

/**
 * @author youly
 * 2019/11/20 11:30
 */
public class CodeGenException extends Exception {

    public CodeGenException(String message) {
        super(message);
    }

    public CodeGenException(String message, Throwable cause) {
        super(message, cause);
    }

    public CodeGenException(Throwable cause) {
        super(cause);
    }
}
