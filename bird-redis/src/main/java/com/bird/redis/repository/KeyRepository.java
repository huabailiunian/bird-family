package com.bird.redis.repository;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author master
 * @date 2020-05-12 10:59
 */
public interface KeyRepository<T> {

    /**
     * delete key
     */
    long del(final T key);

    /**
     * check key
     */
    boolean exists(final T key);

    /**
     * search keys
     */
    Set<T> findKeyByPattern(final T patten);

    /**
     * 设置key的存活时间
     */
    boolean setExpire(final T key, long timeToLive, TimeUnit timeUnit);

    /**
     * 设置key的过期时间
     */
    boolean setExpireAt(final T key, long timestamp);
}
