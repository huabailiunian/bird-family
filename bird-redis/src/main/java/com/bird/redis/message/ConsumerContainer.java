package com.bird.redis.message;

import org.springframework.context.SmartLifecycle;

/**
 * @author youly
 * 2019/8/16 16:55
 */
public interface ConsumerContainer extends SmartLifecycle {

    Object poll(String queue) throws InterruptedException;
}
