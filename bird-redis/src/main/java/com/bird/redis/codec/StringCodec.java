package com.bird.redis.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import org.redisson.client.codec.BaseCodec;
import org.redisson.client.protocol.Decoder;
import org.redisson.client.protocol.Encoder;

import java.nio.charset.StandardCharsets;

/**
 * @author youly
 * 2019/6/28 11:48
 */
public class StringCodec extends BaseCodec {

    static final StringCodec INSTANCE = new StringCodec();

    private final Encoder encoder;
    private final Decoder<Object> decoder;

    private StringCodec() {
        this.encoder = obj -> {
            ByteBuf out = ByteBufAllocator.DEFAULT.buffer();
            out.writeCharSequence(obj.toString(), StandardCharsets.UTF_8);
            return out;
        };
        this.decoder = (buf, state) -> {
            String str = buf.toString(StandardCharsets.UTF_8);
            buf.readerIndex(buf.readableBytes());
            return str;
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
