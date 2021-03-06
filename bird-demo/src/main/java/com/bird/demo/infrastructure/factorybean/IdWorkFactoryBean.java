package com.bird.demo.infrastructure.factorybean;

import com.bird.commons.tools.IdWorker;
import com.bird.redis.client.RedisClient;
import com.bird.zookeeper.service.ZkService;
import com.bird.zookeeper.utils.ZkPathUtils;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.FactoryBean;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author master
 * @date 2020-03-25 14:24
 */
@Slf4j
public class IdWorkFactoryBean implements FactoryBean<IdWorker> {

    private static final long MAX_WORKER_ID = IdWorker.MAX_WORKER_ID;

    /**
     * 数据中心ID
     */
    private long centerId = 0;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * zk服务
     */
    private ZkService zkService;

    /**
     * redis服务
     */
    private RedisClient redisClient;

    /**
     * zk id路径
     */
    private String zkPath = "temp/worker/id";

    /**
     * redis 锁key
     */
    private String lockKey = "temp:worker:id:lock";

    private static final long WAIT_TIME_OUT = 5000L;

    @Override
    public IdWorker getObject() throws Exception {
        long workId = this.nextWorkId();
        return new IdWorker(this.centerId, workId);
    }

    private long nextWorkId() throws Exception {
        RLock lock = redisClient.getLock(lockKey);
        try {
            lock.tryLock(WAIT_TIME_OUT, TimeUnit.MILLISECONDS);
            List<String> children = Collections.emptyList();
            String zkPath = ZkPathUtils.cleanPath(this.zkPath, appName);
            boolean exist = zkService.checkExist(zkPath);
            if (exist) {
                children = zkService.getChildren(zkPath);
            }
            for (int i = 0; i <= MAX_WORKER_ID; i++) {
                String id = String.valueOf(i);
                if (children.contains(id)) {
                    continue;
                }
                String node = ZkPathUtils.getPath(zkPath, id);
                String path = zkService.create(node, Boolean.TRUE, Boolean.FALSE, Boolean.TRUE);
                log.info("分布式ID生成器建立zk节点path:{}, workerId:{}", path, i);
                return i;
            }
            throw new BeanInitializationException("IdWorker init failed");
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Class<?> getObjectType() {
        return IdWorker.class;
    }

    public long getCenterId() {
        return centerId;
    }

    public void setCenterId(long centerId) {
        this.centerId = centerId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public ZkService getZkService() {
        return zkService;
    }

    public void setZkService(ZkService zkService) {
        this.zkService = zkService;
    }

    public RedisClient getRedisClient() {
        return redisClient;
    }

    public void setRedisClient(RedisClient redisClient) {
        this.redisClient = redisClient;
    }

    public String getZkPath() {
        return zkPath;
    }

    public void setZkPath(String zkPath) {
        this.zkPath = zkPath;
    }

    public String getLockKey() {
        return lockKey;
    }

    public void setLockKey(String lockKey) {
        this.lockKey = lockKey;
    }
}
