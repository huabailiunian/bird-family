package com.bird.redis.codec;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import org.redisson.client.codec.BaseCodec;
import org.redisson.client.protocol.Decoder;
import org.redisson.client.protocol.Encoder;

import java.io.IOException;

/**
 * FastJson序列化
 *
 * @author youly
 * 2018/12/26 16:15
 */
public class FastJsonCodec extends BaseCodec {

    static final FastJsonCodec INSTANCE = new FastJsonCodec();

    private final Encoder encoder;
    private final Decoder<Object> decoder;

    private FastJsonCodec() {
        this.encoder = obj -> {
            ByteBuf out = ByteBufAllocator.DEFAULT.buffer();
            try {
                byte[] bytes = JSON.toJSONBytes(obj, SerializerFeature.WriteClassName);
                out.writeBytes(bytes);
                return out;
            } catch (Exception e) {
                out.release();
                throw new IOException(e);
            }
        };
        this.decoder = (buf, state) -> {
            byte[] result = new byte[buf.readableBytes()];
            buf.readBytes(result);
            return JSON.parse(result, Feature.SupportAutoType);
        };
    }

    @Override
    public Decoder<Object> getValueDecoder() {
        return this.decoder;
    }

    @Override
    public Encoder getValueEncoder() {
        return this.encoder;
    }
}
