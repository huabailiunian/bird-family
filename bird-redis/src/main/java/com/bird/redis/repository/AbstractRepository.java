package com.bird.redis.repository;

import com.bird.redis.annotation.RedisRepository;
import com.bird.redis.client.RedisClient;
import com.bird.redis.codec.CodecFactory;
import com.bird.redis.utils.ClassTypeUtils;
import org.redisson.api.*;
import org.redisson.client.codec.Codec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author youly
 * @version 1.0
 * 2018/5/28 16:20
 */
public abstract class AbstractRepository<T> implements KeyOperation {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RedisClient redisClient;
    /**
     * key 空间
     * 影响数据存储
     * 不影响分布式锁&发布/订阅消息
     */
    private String keySpace;
    /**
     * 序列化
     */
    private Codec codec;

    @PostConstruct
    public void postConstruct() {
        RedisRepository rr = this.getClass().getAnnotation(RedisRepository.class);
        if (rr != null) {
            keySpace = rr.keySpace();
        }
        Class<?> type = ClassTypeUtils.superGenericType(this);
        codec = CodecFactory.getCodec(type);
    }

    @Override
    public long del(String key) {
        String redisKey = this.getKey(key);
        if (logger.isDebugEnabled()) {
            logger.debug("delete redis key:{}", key);
        }
        return redisClient.getClient().getKeys().delete(redisKey);
    }

    @Override
    public boolean exists(String key) {
        boolean exists = this.getBucket(key).isExists();
        if (logger.isDebugEnabled()) {
            logger.debug("check exists redis key:{},result:{}", key, exists);
        }
        return exists;
    }

    @Override
    public Set<String> findKeysByPattern(String patten) {
        Set<String> data = new HashSet<>();
        final int prefixLen = redisClient.getKey(keySpace).length() + 1;
        String redisPattern = redisClient.getKey(patten);
        Iterable<String> keys = getClient().getKeys().getKeysByPattern(redisPattern);
        keys.forEach(key -> {
            String subKey = key.substring(prefixLen);
            data.add(subKey);
        });
        return data;
    }

    @Override
    public boolean setExpire(String key, long timeToLive, TimeUnit timeUnit) {
        return this.getClient().getKeys().expire(getKey(key), timeToLive, timeUnit);
    }

    @Override
    public boolean setExpireAt(String key, long timestamp) {
        return this.getClient().getKeys().expireAt(key, timestamp);
    }

    protected String getKey(final String key) {
        if (StringUtils.hasText(keySpace)) {
            return StringUtils.hasText(key) ? redisClient.getKey(keySpace + ":" + key) : redisClient.getKey(keySpace);
        }
        return redisClient.getKey(key);
    }

    protected RBucket<T> getBucket(String key) {
        return getClient().getBucket(getKey(key), this.getCodec());
    }

    protected RMap<String, T> getMap(String key) {
        return getClient().getMap(getKey(key), this.getCodec());
    }

    protected RSet<T> getSet(String key) {
        return getClient().getSet(getKey(key), this.getCodec());
    }

    protected RList<T> getList(String key) {
        return getClient().getList(getKey(key), this.getCodec());
    }

    protected RTopic<T> getTopic(String topic) {
        return getClient().getTopic(redisClient.getKey(topic), CodecFactory.defaultCodec());
    }


    protected RLock getLock(String key) {
        return getClient().getLock(redisClient.getKey(key));
    }

    protected RAtomicLong getAtomicLong(String key) {
        return getClient().getAtomicLong(getKey(key));
    }

    protected RAtomicDouble getAtomicDouble(String key) {
        return getClient().getAtomicDouble(getKey(key));
    }


    public RedisClient getRedisClient() {
        return redisClient;
    }

    public void setRedisClient(RedisClient redisClient) {
        this.redisClient = redisClient;
    }

    public String getKeySpace() {
        return keySpace;
    }

    public void setKeySpace(String keySpace) {
        this.keySpace = keySpace;
    }

    public Codec getCodec() {
        return codec;
    }

    private void setCodec(Codec codec) {
        this.codec = codec;
    }

    protected RedissonClient getClient() {
        return redisClient.getClient();
    }
}
