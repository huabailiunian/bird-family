package com.bird.redis.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import org.redisson.client.codec.BaseCodec;
import org.redisson.client.protocol.Decoder;
import org.redisson.client.protocol.Encoder;

/**
 * 字节数组序列化
 *
 * @author youly
 * 2018/12/26 16:13
 */
public class ByteArrayCodec extends BaseCodec {

    static final ByteArrayCodec INSTANCE = new ByteArrayCodec();

    private final Encoder encoder = obj -> {
        byte[] payload = (byte[]) obj;
        ByteBuf out = ByteBufAllocator.DEFAULT.buffer(payload.length);
        out.writeBytes(payload);
        return out;
    };
    private final Decoder<Object> decoder = (buf, state) -> {
        byte[] result = new byte[buf.readableBytes()];
        buf.readBytes(result);
        return result;
    };

    @Override
    public Decoder<Object> getValueDecoder() {
        return this.decoder;
    }

    @Override
    public Encoder getValueEncoder() {
        return this.encoder;
    }

    @Override
    public Decoder<Object> getMapKeyDecoder() {
        return StringCodec.INSTANCE.getValueDecoder();
    }

    @Override
    public Encoder getMapKeyEncoder() {
        return StringCodec.INSTANCE.getValueEncoder();
    }
}
