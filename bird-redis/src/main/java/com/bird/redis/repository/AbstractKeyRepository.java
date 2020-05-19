package com.bird.redis.repository;

import com.bird.redis.client.RedisClient;
import com.bird.redis.codec.CodecFactory;
import lombok.extern.slf4j.Slf4j;
import org.redisson.client.codec.Codec;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author master
 * @date 2020-05-12 11:04
 */
@Slf4j
public abstract class AbstractKeyRepository implements KeyRepository<String> {

    /**
     * 命名空间
     */
    private String namespace;
    private Codec codec;
    private RedisClient redisClient;

    @PostConstruct
    public void setUp() {
        RedisRepository annotation = this.getClass().getAnnotation(RedisRepository.class);
        if (Objects.nonNull(annotation)) {
            namespace = annotation.namespace();
        }
        codec = CodecFactory.getCodec(this.getClass());
    }


    @Override
    public long del(String key) {
        String redisKey = this.getKey(key);
        if (log.isDebugEnabled()) {
            log.debug("delete redis key: {}", redisKey);
        }
        return redisClient.getKeys().delete(redisKey);
    }

    @Override
    public boolean exists(String key) {
        String redisKey = this.getKey(key);
        boolean exists = redisClient.getBucket(redisKey).isExists();
        if (log.isDebugEnabled()) {
            log.debug("check exists redis key:{},result:{}", redisKey, exists);
        }
        return exists;
    }

    @Override
    public Set<String> findKeyByPattern(String patten) {
        Set<String> result = new HashSet<>();
        int prefix = redisClient.getGroup().length() + 1;
        String redisPattern = this.getKey(patten);
        Iterable<String> keys = redisClient.getKeys().getKeysByPattern(redisPattern);
        keys.forEach(key -> {
            String substring = key.substring(prefix);
            result.add(substring);
        });
        return result;
    }

    @Override
    public boolean setExpire(String key, long timeToLive, TimeUnit timeUnit) {
        return redisClient.getKeys().expire(this.getKey(key), timeToLive, timeUnit);
    }

    @Override
    public boolean setExpireAt(String key, long timestamp) {
        return redisClient.getKeys().expireAt(this.getKey(key), timestamp);
    }

    protected String getKey(final String key) {
        if (StringUtils.hasText(namespace)) {
            return StringUtils.hasText(key) ? namespace + ":" + key : namespace;
        }
        return key;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public Codec getCodec() {
        return codec;
    }

    public void setCodec(Codec codec) {
        this.codec = codec;
    }

    public RedisClient getRedisClient() {
        return redisClient;
    }

    public void setRedisClient(RedisClient redisClient) {
        this.redisClient = redisClient;
    }
}
