package com.bird.redis.repository;

import java.util.Map;
import java.util.Set;

/**
 * @author master
 * on 6/16/2017
 */
public abstract class AbstractHashRepository<T> extends AbstractRepository<T> implements HashRepository<T, String> {

    @Override
    public T hGet(final String key, final String field) {
        return getMap(key).get(field);
    }

    @Override
    public Map<String, T> hMGet(final String key, final Set<String> fields) {
        return getMap(key).getAll(fields);
    }

    @Override
    public boolean hSet(final String key, final String field, final T value) {
        return getMap(key).fastPut(field, value);
    }

    @Override
    public void hMSet(final String key, final Map<String, T> values) {
        getMap(key).putAll(values);
    }

    @Override
    public long hRemove(final String key, final String... field) {
        return getMap(key).fastRemove(field);
    }

    @Override
    public boolean hExists(final String key, final String field) {
        return getMap(key).containsKey(field);
    }

    @Override
    public Set<String> hKeys(final String key) {
        return getMap(key).keySet();
    }

    @Override
    public Set<Map.Entry<String, T>> hGetAllEntrySet(final String key) {
        return getMap(key).readAllEntrySet();
    }

    @Override
    public Map<String, T> hGetAll(String key) {
        return getMap(key).readAllMap();
    }
}
