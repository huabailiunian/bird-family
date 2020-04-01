package com.bird.redis.codec;

import com.bird.core.consts.GlobalConst;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import org.redisson.client.codec.BaseCodec;
import org.redisson.client.protocol.Decoder;
import org.redisson.client.protocol.Encoder;

import java.nio.charset.Charset;

/**
 * @author youly
 * 2019/6/28 11:48
 */
public class StringCodec extends BaseCodec {

    static final StringCodec INSTANCE = new StringCodec();

    private final Charset charset = GlobalConst.CHARSET_UTF8;
    private final Encoder encoder;
    private final Decoder<Object> decoder;

    private StringCodec() {
        this.encoder = obj -> {
            ByteBuf out = ByteBufAllocator.DEFAULT.buffer();
            out.writeCharSequence(obj.toString(), StringCodec.this.charset);
            return out;
        };
        this.decoder = (buf, state) -> {
            String str = buf.toString(StringCodec.this.charset);
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
