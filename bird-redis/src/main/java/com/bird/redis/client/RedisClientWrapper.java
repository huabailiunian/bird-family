package com.bird.redis.client;

import com.bird.redis.codec.CodecFactory;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RedissonClient;

/**
 * Redis客户端包装
 *
 * @author youly
 * 2018/12/26 15:45
 */
public class RedisClientWrapper {

    private String keyGroup = "dev";

    private RedissonClient redisClient;

    public RedisClientWrapper(RedissonClient redisClient) {
        this.redisClient = redisClient;
    }

    public String getKeyGroup() {
        return keyGroup;
    }

    public void setKeyGroup(String keyGroup) {
        this.keyGroup = keyGroup;
    }

    public RedissonClient getRedisClient() {
        return redisClient;
    }

    public void setRedisClient(RedissonClient redisClient) {
        this.redisClient = redisClient;
    }

    public String getKey(String key) {
        return StringUtils.isBlank(key) ? this.keyGroup : this.keyGroup + ":" + key;
    }

    public RBlockingQueue<Object> getBlockingQueue(String queue) {
        return this.redisClient.getBlockingQueue(this.getKey(queue), CodecFactory.defaultCodec());
    }
}
