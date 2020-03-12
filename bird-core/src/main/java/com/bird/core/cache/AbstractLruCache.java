package com.bird.core.cache;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * 最近最少使用淘汰
 *
 * @author youly
 * 2019/5/9 15:59
 */
public abstract class AbstractLruCache<K, V> implements ICache<K, V> {

    private static final int DEFAULT_CACHE_SIZE = 16;

    private static final float LOAD_FACTOR = 0.75f;

    private static final boolean ACCESS_ORDER = true;

    private Map<K, V> cache;

    public AbstractLruCache() {
        this(DEFAULT_CACHE_SIZE);
    }

    public AbstractLruCache(Integer cacheSize) {
        int initCapacity = cacheSize + 1;
        this.cache = new LinkedHashMap<K, V>(initCapacity, LOAD_FACTOR, ACCESS_ORDER) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return this.size() >= cacheSize;
            }
        };
        this.cache = Collections.synchronizedMap(cache);
    }

    @Override
    public V get(K key) {
        return cache.get(key);
    }

    @Override
    public void set(K key, V val) {
        cache.put(key, val);
    }

    @Override
    public void clear() {
        cache.clear();
    }

    @Override
    public void remove(K key) {
        cache.remove(key);
    }

    @Override
    public boolean exists(K key) {
        return cache.containsKey(key);
    }

    @Override
    public Set<K> keySet() {
        return cache.keySet();
    }
}
