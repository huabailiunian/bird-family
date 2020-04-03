package com.bird.demo.config;

import com.bird.core.tools.IdWorker;
import com.bird.demo.factory.bean.IdWorkFactoryBean;
import com.bird.redis.client.RedisClient;
import com.bird.zookeeper.service.ZkService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author master
 * @date 2020-03-26 14:39
 */
@Configuration
public class IdWorkConfig {

    @Bean
    public IdWorker idWorker(RedisClient redisClient, ZkService zkService) throws Exception {
        IdWorkFactoryBean factoryBean = new IdWorkFactoryBean();
        factoryBean.setCenterId(0);
        factoryBean.setRedisClient(redisClient);
        factoryBean.setZkService(zkService);
        return factoryBean.getObject();
    }

}
