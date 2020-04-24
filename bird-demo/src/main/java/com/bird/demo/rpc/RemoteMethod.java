package com.bird.demo.rpc;

import java.lang.annotation.*;

/**
 * @author master
 * @date 2020-04-22 17:23
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface RemoteMethod {

    /**
     * 服务路径
     *
     * @return 路径
     */
    String path() default "/";
}
