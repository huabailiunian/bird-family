package com.bird.redis.message;

import java.util.EventListener;

/**
 * @author youly
 * 2019/7/1 16:16
 */
public interface RedisConsumer<M> extends EventListener {

    String getQueue();

    void onMessage(String queue, M msg);
}
