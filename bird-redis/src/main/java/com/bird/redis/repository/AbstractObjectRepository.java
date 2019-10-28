package com.bird.redis.repository;

import java.util.concurrent.TimeUnit;

/**
 * @author master
 * on 7/13/2017
 */
public abstract class AbstractObjectRepository<T> extends AbstractRepository<T> implements ObjectRepository<T> {

    @Override
    public T get(final String key) {
        return getBucket(key).get();
    }

    @Override
    public void set(final String key, final T value) {
        getBucket(key).trySet(value);
    }

    @Override
    public boolean trySet(String key, T value) {
        return getBucket(key).trySet(value);
    }

    @Override
    public boolean trySet(String key, T value, long timeToLive, TimeUnit timeUnit) {
        return getBucket(key).trySet(value, timeToLive, timeUnit);
    }

    @Override
    public long incr(final String key) {
        return getAtomicLong(key).incrementAndGet();
    }

    @Override
    public long incrby(final String key, final long increment) {
        return getAtomicLong(key).addAndGet(increment);
    }

    @Override
    public double incrby(final String key, final double increment) {
        return getAtomicDouble(key).addAndGet(increment);
    }
}
