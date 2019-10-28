/*
 * Copyright (c) 2017. RisString Control System(RCS)
 */

package com.bird.redis.repository;

import java.util.Map;
import java.util.Set;

/**
 * Redis 的 Hash 操作
 *
 * @param <T> Hash 的 value 的类型
 * @author master
 * on 6/16/2017
 */
public interface HashRepository<T, F> extends KeyOperation {

    /**
     * 返回哈希表 key 中给定域 field 的值。
     *
     * @param key
     * @param field
     * @return 给定域的值。当给定域不存在或是给定 key 不存在时，返回 null 。
     */
    T hGet(final String key, F field);

    /**
     * 返回哈希表 key 中给定域 field 的值，并在本地缓存。
     * @param key
     * @param field
     * @return 给定域的值。当给定域不存在或是给定 key 不存在时，返回 null 。
     */
//    T hGetWithCache(final String key, F field);

    /**
     * 返回 Hash 给定多个域的值，不存在的域会被忽略
     *
     * @param key
     * @param fields
     * @return 一个包含多个给定域的关联值的表，表值的排列顺序和给定域参数的请求顺序一样
     */
    Map<String, T> hMGet(final String key, final Set<F> fields);

    /**
     * 返回 Hash 给定多个域的值，不存在的域会被忽略，并在本地缓存
     * @param key
     * @param fields
     * @return 一个包含多个给定域的关联值的表，表值的排列顺序和给定域参数的请求顺序一样
     */
//    Map<String, T> hMGetWithCache(final String key, final Set<F> fields);

    /**
     * 将哈希表 key 中的域 field 的值设为 value 。
     * 如果 key 不存在，一个新的哈希表被创建并进行 HSET 操作。
     * 如果域 field 已经存在于哈希表中，旧值将被覆盖。
     *
     * @param key
     * @param field
     * @param value
     * @return 如果 field 是哈希表中的一个新建域，并且值设置成功，返回 1 。
     * 如果哈希表中域 field 已经存在且旧值已被新值覆盖，返回 0 。
     */
    boolean hSet(final String key, F field, T value);

    /**
     * 同时将多个 field-value (域-值)对设置到哈希表 key 中。
     * 此命令会覆盖哈希表中已存在的域。
     * 如果 key 不存在，一个空哈希表被创建并执行 HMSET 操作
     *
     * @param key
     * @param values
     */
    void hMSet(String key, Map<F, T> values);

    /**
     * 删除哈希表 key 中的一个指定域，不存在的域将被忽略
     *
     * @param key
     * @param field
     * @return
     */
    long hRemove(final String key, F... field);

    /**
     * 查看哈希表 key 中，给定域 field 是否存在
     *
     * @param key
     * @param field
     * @return
     */
    boolean hExists(String key, F field);

    /**
     * 返回哈希表 key 中的所有域
     *
     * @param key
     * @return
     */
    Set<F> hKeys(String key);

    /**
     * 获取 hash 中指定 key 的所有数据
     *
     * @param key
     * @return
     */
    Set<Map.Entry<String, T>> hGetAllEntrySet(final String key);

    /**
     * 获取 hash 中指定 key 的所有数据
     *
     * @param key
     * @return
     */
    Map<String, T> hGetAll(final String key);
}
