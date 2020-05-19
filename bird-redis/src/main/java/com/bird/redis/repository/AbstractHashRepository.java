package com.bird.redis.repository;

import org.redisson.api.RMap;

import java.util.Map;
import java.util.Set;

/**
 * @author master
 * @date 2020-05-12 13:16
 */
public abstract class AbstractHashRepository<V> extends AbstractKeyRepository implements HashRepository<V, String> {

    @Override
    public V hGet(String key, String field) {
        return this.getMap(key).get(field);
    }

    @Override
    public Map<String, V> hMGet(String key, Set<String> fields) {
        return this.getMap(key).getAll(fields);
    }

    @Override
    public boolean hSet(String key, String field, V value) {
        return this.getMap(key).fastPut(field, value);
    }

    @Override
    public void hMSet(String key, Map<String, V> values) {
        this.getMap(key).putAll(values);
    }

    @Override
    public long hRemove(String key, String... field) {
        return this.getMap(key).fastRemove(field);
    }

    @Override
    public boolean hExists(String key, String field) {
        return this.getMap(key).containsKey(field);
    }

    @Override
    public Set<String> hKeys(String key) {
        return this.getMap(key).readAllKeySet();
    }

    @Override
    public Set<Map.Entry<String, V>> hEntrySet(String key) {
        return this.getMap(key).readAllEntrySet();
    }

    @Override
    public Map<String, V> hGetAll(String key) {
        return this.getMap(key).readAllMap();
    }

    protected RMap<String, V> getMap(String key) {
        return this.getRedisClient().getMap(this.getKey(key), this.getCodec());
    }
}
