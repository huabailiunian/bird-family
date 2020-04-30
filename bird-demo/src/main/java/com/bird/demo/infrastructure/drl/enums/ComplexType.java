package com.bird.demo.infrastructure.drl.enums;

/**
 * @author youly
 * 2019/7/19 14:20
 */
public enum ComplexType {

    NONE(", "), AND(" && "), OR(" || ");

    private String operation;

    ComplexType(String operation) {
        this.operation = operation;
    }

    public String operation() {
        return this.operation;
    }
}
