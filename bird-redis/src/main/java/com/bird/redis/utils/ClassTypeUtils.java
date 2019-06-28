package com.bird.redis.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author youly
 * 2018/12/26 18:03
 */
public class ClassTypeUtils {

    public static Class<?> superGenericType(Object object) {
        if (null == object) {
            return Object.class;
        }
        Type genericSuperclass = object.getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            return (Class) parameterizedType.getActualTypeArguments()[0];
        } else {
            return Object.class;
        }
    }
}
