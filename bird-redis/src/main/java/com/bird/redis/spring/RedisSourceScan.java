package com.bird.redis.spring;

import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author youly
 * 2019/5/16 20:38
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(RedisSourceScannerRegistrar.class)
public @interface RedisSourceScan {

    @AliasFor(attribute = "basePackages")
    String[] value() default {};

    @AliasFor(attribute = "value")
    String[] basePackages() default {};

    Class<?>[] basePackageClasses() default {};

    boolean scanRepository() default true;

    boolean scanConsumer() default true;
}
