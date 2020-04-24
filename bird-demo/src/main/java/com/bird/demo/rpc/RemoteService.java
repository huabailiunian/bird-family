package com.bird.demo.rpc;

import java.lang.annotation.*;

/**
 * @author master
 * @date 2020-04-22 17:23
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface RemoteService {

    /**
     * 服务名
     *
     * @return 服务名
     */
    String name() default "default";

    /**
     * 服务根路径
     *
     * @return 路径
     */
    String path() default "/";
}
