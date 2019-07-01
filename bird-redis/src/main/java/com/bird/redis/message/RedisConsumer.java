package com.bird.redis.message;

import com.bird.core.message.MessageConsumer;

/**
 * @author youly
 * 2019/7/1 16:16
 */
public interface RedisConsumer<M> extends MessageConsumer<M> {

    String getQueue();
}
