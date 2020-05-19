package com.bird.redis.spring.boot;

import com.bird.redis.client.RedisClient;
import com.bird.redis.codec.CodecFactory;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * Redis 客户端配置类
 *
 * @author youly
 * @date 2019/1/2 14:32
 */
@Configuration
@ConditionalOnClass({RedissonClient.class, Redisson.class})
@ConditionalOnProperty(prefix = RedisProperties.PREFIX, name = "auto", havingValue = "true", matchIfMissing = false)
@EnableConfigurationProperties({RedisProperties.class})
public class RedisAutoConfiguration {

    private RedisProperties redisProperties;

    public RedisAutoConfiguration(RedisProperties redisProperties) {
        this.redisProperties = redisProperties;
    }

    @Bean
    public RedisClient redisClient() {
        RedisClient redisClient = new RedisClient(getRedisConfig());
        String group = redisProperties.getGroup();
        if (StringUtils.hasText(group)) {
            redisClient.setGroup(group);
        }
        return redisClient;
    }

    private Config getRedisConfig() {
        Config config = new Config();
        config.setCodec(CodecFactory.defaultCodec());
        String password = redisProperties.getPassword();
        RedisProperties.Cluster cluster = redisProperties.getCluster();
        if (Objects.nonNull(cluster)) {
            ClusterServersConfig clusterServers = config.useClusterServers();
            String[] nodes = cluster.getNodes().stream().map(AddressValidator::validate).toArray(String[]::new);
            clusterServers.addNodeAddress(nodes);
            clusterServers.setScanInterval(cluster.getScanInterval()).setReadMode(cluster.getReadMode());
            clusterServers.setMasterConnectionMinimumIdleSize(cluster.getMasterPool().getMinIdleSize())
                    .setMasterConnectionPoolSize(cluster.getMasterPool().getPoolSize())
                    .setSlaveConnectionMinimumIdleSize(cluster.getSlavePool().getMinIdleSize())
                    .setSlaveConnectionPoolSize(cluster.getSlavePool().getPoolSize());
            if (StringUtils.hasText(password)) {
                clusterServers.setPassword(password);
            }
        } else {
            RedisProperties.Single single = redisProperties.getSingle();
            if (Objects.nonNull(single)) {
                SingleServerConfig singleServer = config.useSingleServer();
                singleServer.setAddress(AddressValidator.validate(single.getHost()))
                        .setConnectionMinimumIdleSize(single.getPool().getMinIdleSize())
                        .setConnectionPoolSize(single.getPool().getPoolSize());
                if (StringUtils.hasText(password)) {
                    singleServer.setPassword(password);
                }
            }
        }
        return config;
    }
}
