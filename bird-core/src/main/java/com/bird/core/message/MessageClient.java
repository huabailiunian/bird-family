package com.bird.core.message;

import com.bird.core.IClient;

/**
 * 消息生产者
 *
 * @author youly
 * 2019/7/1 15:55
 */
public interface MessageClient extends IClient {

    boolean send(String queue, Object msg);


}
