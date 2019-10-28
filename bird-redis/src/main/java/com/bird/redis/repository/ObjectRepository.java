package com.bird.redis.repository;


import java.util.concurrent.TimeUnit;

/**
 * Redis 的 String 操作
 *
 * @author master
 * on 7/13/2017
 */
public interface ObjectRepository<T> extends KeyOperation {

    /**
     * 返回 key 所关联的对象。
     * 如果 key 不存在那么返回特殊值 null 。
     *
     * @param key
     * @return
     */
    T get(String key);

    /**
     * 将字符串值 value 关联到 key 。
     * 如果 key 已经持有其他值， SET 就覆写旧值，无视类型。
     * 对于某个原本带有生存时间（TTL）的键来说， 当 SET 命令成功在这个键上执行时， 这个键原有的 TTL 将被清除。
     *
     * @param key
     * @param value
     * @return
     */
    void set(String key, T value);

    /**
     * 向指定 key 中尝试放入 value，当key 不存在时成功
     *
     * @param key
     * @param value
     * @return
     */
    boolean trySet(String key, T value);

    /**
     * 向指定 key 中尝试放入 value，当key 不存在时成功
     *
     * @param key
     * @param value
     * @param timeToLive key 的存活时间
     * @param timeUnit
     * @return
     */
    boolean trySet(String key, T value, long timeToLive, TimeUnit timeUnit);

    /**
     * 将 key 中储存的数字值增一。
     * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCR 操作。
     * 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。
     *
     * @param key
     * @return 执行 INCR 命令之后 key 的值。
     */
    long incr(String key);

    /**
     * 将 key 所储存的值加上增量 increment 。
     * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCRBY 命令。
     * 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误
     *
     * @param key
     * @param increment
     * @return 加上 increment 之后，key 的值
     */
    long incrby(String key, long increment);

    /**
     * 将 key 所储存的值加上增量 increment 。
     * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCRBY 命令。
     * 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误
     *
     * @param key
     * @param increment
     * @return 加上 increment 之后， key 的值
     */
    double incrby(String key, double increment);

    /**
     *
     * @param key
     * @param value
     * @param expiration 可以为 null，为 null 时等同于{@link Expiration#persistent()}
     * @param option 可以为 null，为 null 时等同于{@link RedisStringCommands.SetOption#UPSERT}
     * @return 设置成功返回 true, 否则返回 false
     */
//    boolean set(String key, T value, Expiration expiration, RedisStringCommands.SetOption option);

}
