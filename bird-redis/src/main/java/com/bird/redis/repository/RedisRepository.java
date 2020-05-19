package com.bird.redis.repository;

import java.lang.annotation.*;

/**
 * @author master
 * @date 2020-05-12 10:56
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisRepository {

    /**
     * eg：namespace = "user:cache"
     * @return key命名空间
     */
    String namespace();

}
