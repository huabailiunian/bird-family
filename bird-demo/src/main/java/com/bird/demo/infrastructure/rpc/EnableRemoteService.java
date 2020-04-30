package com.bird.demo.infrastructure.rpc;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author master
 * @date 2020-04-22 17:23
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import({RemoteServiceImportRegistrar.class})
public @interface EnableRemoteService {

    /**
     * 包路径
     *
     * @return
     */
    String[] basePackages();
}


