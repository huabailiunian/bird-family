package com.bird.demo.config;

import com.bird.core.tools.IdWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author master
 * @date 2020-03-26 14:39
 */
@Configuration
public class IdWorkConfig {

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(0,1);
    }

}
