package com.bird.core.message;

import com.bird.core.IConsumer;

/**
 * 消息消费者
 *
 * @author youly
 * 2019/7/1 15:58
 */
public interface MessageConsumer<M> extends IConsumer {

    void onMessage(String queue, M msg);

}
