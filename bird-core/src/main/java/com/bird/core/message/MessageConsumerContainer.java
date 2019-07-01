package com.bird.core.message;

import org.springframework.context.SmartLifecycle;

/**
 * 消息消费者容器
 *
 * @author youly
 * 2019/7/1 16:02
 */
public interface MessageConsumerContainer extends SmartLifecycle {

    Object poll(String queue) throws InterruptedException;
}
