package com.bird.redis.message;

/**
 * @author youly
 * 2019/8/16 17:05
 */
public interface MQClient {

    boolean send(String queue, Object msg);
}
