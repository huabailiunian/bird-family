package com.bird.redis.repository;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author youly
 * 2019/10/28 15:12
 */
public interface KeyOperation {

    /**
     * delete key
     */
    long del(final String key);

    /**
     * check key
     */
    boolean exists(final String key);

    /**
     * search keys
     */
    Set<String> findKeysByPattern(final String patten);

    /**
     * 设置key的存活时间
     */
    boolean setExpire(final String key, long timeToLive, TimeUnit timeUnit);

    /**
     * 设置key的过期时间
     */
    boolean setExpireAt(final String key, long timestamp);
}
