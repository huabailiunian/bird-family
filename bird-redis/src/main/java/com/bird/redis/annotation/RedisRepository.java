package com.bird.redis.annotation;

import java.lang.annotation.*;

/**
 * @author youly
 * 2018/12/26 17:25
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
@Documented
public @interface RedisRepository {
    String keySpace();
}
