package com.bird.core.enums;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * @author youly
 * 2019/11/21 14:17
 */
public enum TypeEnum {

    BYTE("Byte", Byte.class.getName()),
    SHORT("Short", Short.class.getName()),
    INTEGER("Integer", Integer.class.getName()),
    LONG("Long", Long.class.getName()),
    FLOAT("Float", Float.class.getName()),
    DOUBLE("Double", Double.class.getName()),
    BOOLEAN("Boolean", Boolean.class.getName()),
    STRING("String", String.class.getName()),
    DATE("Date", Date.class.getName()),
    CHARACTER("Character", Character.class.getName()),
    DECIMAL("BigDecimal", BigDecimal.class.getName()),
    BIG_INTEGER("BigInteger", BigInteger.class.getName());

    private String shortName;
    private String className;

    TypeEnum(String shortName, String className) {
        this.shortName = shortName;
        this.className = className;
    }

    public String shortName() {
        return shortName;
    }

    public String className() {
        return className;
    }
}
