package com.bird.redis.message.impl;

import com.bird.redis.client.RedisClient;
import com.bird.redis.message.MQClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * redis MQ 客户端
 *
 * @author youly
 * 2019/7/1 16:15
 */
public class RedisMQClient implements MQClient {

    private Logger logger = LoggerFactory.getLogger(RedisMQClient.class);

    private RedisClient redisClient;

    /**
     * 消息超时时间 单位：毫秒
     */
    private long timeout = 10000L;

    public RedisMQClient(RedisClient redisClient) {
        this.redisClient = redisClient;
    }

    public RedisClient getRedisClient() {
        return redisClient;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    @Override
    public boolean send(String queue, Object msg) {
        try {
            if (this.redisClient.getBlockingQueue(queue).offer(msg, this.timeout, TimeUnit.MILLISECONDS)) {
                if (logger.isInfoEnabled()) {
                    logger.info("Queue[{}]-Message[{}]发送成功", queue, msg.toString());
                }
                return true;
            } else {
                if (logger.isWarnEnabled()) {
                    logger.warn("Queue[{}]-Message[{}]发送失败", queue, msg.toString());
                }
            }
        } catch (InterruptedException e) {
            if (logger.isWarnEnabled()) {
                logger.warn("Queue[{}]-Message[{}]发送失败,系统异常中断", queue, msg.toString(), e);
            }
        }
        return false;
    }
}
