package com.bird.redis.message;

import java.lang.annotation.*;

/**
 * @author youly
 * 2019/5/15 14:32
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
@Documented
public @interface MQConsumer {
}
