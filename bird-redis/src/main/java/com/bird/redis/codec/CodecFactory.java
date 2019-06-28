package com.bird.redis.codec;

import org.redisson.client.codec.Codec;

/**
 * @author youly
 * 2019/6/28 13:38
 */
public class CodecFactory {

    /**
     * 默认使用FastJson序列化
     */
    public static Codec defaultCodec() {
        return FastJsonCodec.INSTANCE;
    }

    /**
     * 根据类型返回序列化方式
     *
     * @param type 存储数据类型
     */
    public static Codec getCodec(Class<?> type) {
        if (type.equals(byte[].class)) {
            return ByteArrayCodec.INSTANCE;
        } else if (type.equals(String.class)) {
            return StringCodec.INSTANCE;
        } else {
            return FastJsonCodec.INSTANCE;
        }
    }
}
