package com.bird.commons.cache;

import java.util.Set;

/**
 * 数据缓存
 *
 * @author youly
 * 2019/4/25 11:09
 */
public interface ICache<K, V> {

    /**
     * 读取数据
     *
     * @param key 唯一标识
     * @return 数据
     */
    V get(final K key);

    /**
     * 存储数据
     *
     * @param key 唯一标识
     * @param val 数据
     */
    void set(final K key, final V val);

    /**
     * 清空缓存
     */
    void clear();

    /**
     * 移除缓存
     *
     * @param key 缓存标识
     */
    void remove(final K key);

    /**
     * 是否存在缓存
     *
     * @param key 缓存标识
     * @return true or false
     */
    boolean exists(final K key);

    /**
     * 读取缓存标识集合
     *
     * @return 缓存标识集合
     */
    Set<K> keySet();


}
