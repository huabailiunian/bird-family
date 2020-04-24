package com.bird.codegen.utils;


import com.bird.commons.enums.TypeEnum;

import java.util.HashMap;

/**
 * @author youly
 * 2019/11/21 14:54
 */
public class TypeUtils {

    public static final String BYTE = "byte";
    public static final String SHORT = "short";
    public static final String INT = "int";
    public static final String LONG = "long";
    public static final String FLOAT = "float";
    public static final String DOUBLE = "double";
    public static final String CHAR = "char";
    public static final String BOOLEAN = "boolean";

    private static HashMap<String, String> baseTypeMap = new HashMap<>();

    static {
        for (TypeEnum value : TypeEnum.values()) {
            baseTypeMap.put(value.shortName(), value.className());
        }
        baseTypeMap.put(BYTE, Byte.class.getName());
        baseTypeMap.put(SHORT, Short.class.getName());
        baseTypeMap.put(INT, Integer.class.getName());
        baseTypeMap.put(LONG, Long.class.getName());
        baseTypeMap.put(FLOAT, Float.class.getName());
        baseTypeMap.put(DOUBLE, Double.class.getName());
        baseTypeMap.put(BOOLEAN, Boolean.class.getName());
        baseTypeMap.put(CHAR, Character.class.getName());
    }

    public static HashMap<String, String> getBaseTypeMap() {
        return baseTypeMap;
    }

    public static boolean isBaseType(String className) {
        return baseTypeMap.containsValue(className);
    }

    public static boolean isPrimitiveTypeClass(final String className) {
        //returns true for: byte, short, int, long, float, double, char, boolean
        return Byte.class.getName().equals(className) ||
                Short.class.getName().equals(className) ||
                Integer.class.getName().equals(className) ||
                Long.class.getName().equals(className) ||
                Float.class.getName().equals(className) ||
                Double.class.getName().equals(className) ||
                Character.class.getName().equals(className) ||
                Boolean.class.getName().equals(className);
    }

    public static boolean isPrimitiveTypeId(final String type) {
        //returns true for: byte, short, int, long, float, double, char, boolean
        return BYTE.equals(type) || SHORT.equals(type) || INT.equals(type) ||
                LONG.equals(type) || FLOAT.equals(type) || DOUBLE.equals(type) ||
                CHAR.equals(type) || BOOLEAN.equals(type);
    }

    public static String getClassNameForPrimitiveTypeId(final String typeId) {
        return baseTypeMap.get(typeId);
    }
}
