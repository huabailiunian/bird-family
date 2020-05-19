package com.bird.redis.codec;

import org.redisson.client.codec.Codec;
import org.redisson.codec.JsonJacksonCodec;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author youly
 * 2019/6/28 13:38
 */
public class CodecFactory {

    /**
     * 默认使用Jackson序列化
     */
    public static Codec defaultCodec() {
        return JsonJacksonCodec.INSTANCE;
    }

    /**
     * 根据类型返回序列化方式
     *
     * @param cls 实例类型
     */
    public static Codec getCodec(Class<?> cls) {
        Class type = getGenericType(cls);
        if (type.equals(byte[].class)) {
            return ByteArrayCodec.INSTANCE;
        } else if (type.equals(String.class)) {
            return StringCodec.INSTANCE;
        } else {
            return JsonJacksonCodec.INSTANCE;
        }
    }

    private static Class getGenericType(Class cls) {
        if (cls == Object.class) {
            return cls;
        }
        Type type = cls.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            return (Class) parameterizedType.getActualTypeArguments()[0];
        } else {
            return Object.class;
        }
    }
}
