package com.bird.redis.message;

import com.bird.core.message.MessageClient;
import com.bird.redis.entity.RedisClientWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * redis MQ 客户端
 *
 * @author youly
 * 2019/7/1 16:15
 */
public class RedisMQClient implements MessageClient {

    private Logger logger = LoggerFactory.getLogger(RedisMQClient.class);

    private RedisClientWrapper redisClientWrapper;

    /**
     * 消息超时时间 单位：毫秒
     */
    private long timeout = 10000L;

    public RedisMQClient(RedisClientWrapper redisClientWrapper) {
        this.redisClientWrapper = redisClientWrapper;
    }

    public RedisClientWrapper getRedisClientWrapper() {
        return redisClientWrapper;
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
            if (this.redisClientWrapper.getBlockingQueue(queue).offer(msg, this.timeout, TimeUnit.MILLISECONDS)) {
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
