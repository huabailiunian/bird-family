package com.bird.demo.app.config;

import com.bird.commons.tools.IdWorker;
import com.bird.demo.infrastructure.factorybean.IdWorkFactoryBean;
import com.bird.redis.client.RedisClient;
import com.bird.zookeeper.service.ZkService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author master
 * @date 2020-03-26 14:39
 */
@Configuration
public class IdWorkConfig {

    @Value("${spring.application.name}")
    private String appName;

    @Bean
    public IdWorker idWorker(RedisClient redisClient, ZkService zkService) throws Exception {
        IdWorkFactoryBean factoryBean = new IdWorkFactoryBean();
        factoryBean.setAppName(appName);
        factoryBean.setRedisClient(redisClient);
        factoryBean.setZkService(zkService);
        return factoryBean.getObject();
    }

}
