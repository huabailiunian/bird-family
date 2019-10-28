package com.bird.redis.repository;

import java.util.Set;

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
}
