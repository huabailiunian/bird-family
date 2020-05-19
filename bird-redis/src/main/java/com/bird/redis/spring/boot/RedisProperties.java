package com.bird.redis.spring.boot;

import org.redisson.config.ReadMode;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * redis配置类
 *
 * @author youly
 * 2018/12/29 11:07
 */
@ConfigurationProperties(prefix = RedisProperties.PREFIX)
public class RedisProperties {

    static final String PREFIX = "bird.redis";

    /**
     * 启用自动配置
     */
    private boolean auto;
    /**
     * redis key 分组前缀
     */
    private String group = "dev";
    /**
     * redis 密码
     */
    private String password;

    private Single single = new Single();
    private Cluster cluster;

    public boolean isAuto() {
        return auto;
    }

    public void setAuto(boolean auto) {
        this.auto = auto;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Single getSingle() {
        return single;
    }

    public void setSingle(Single single) {
        this.single = single;
    }

    public Cluster getCluster() {
        return cluster;
    }

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    public static class Pool {

        private int minIdleSize = 10;

        private int poolSize = 50;

        public int getMinIdleSize() {
            return minIdleSize;
        }

        public void setMinIdleSize(int minIdleSize) {
            this.minIdleSize = minIdleSize;
        }

        public int getPoolSize() {
            return poolSize;
        }

        public void setPoolSize(int poolSize) {
            this.poolSize = poolSize;
        }
    }

    /**
     * 单机模式
     */
    public static class Single {

        private String host = "127.0.0.1:6379";

        /**
         * 连接池参数
         */
        private Pool pool = new Pool();

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public Pool getPool() {
            return pool;
        }

        public void setPool(Pool pool) {
            this.pool = pool;
        }
    }

    /**
     * 主从集群模式
     */
    public static class Cluster {

        /**
         * 集群节点 HOST:PORT
         */
        private List<String> nodes;

        /**
         * Redis 集群 扫描间隔(毫秒)
         */
        private Integer scanInterval = 1000;

        /**
         * 集群读取模式
         */
        private ReadMode readMode = ReadMode.SLAVE;

        /**
         * 主节点连接池参数
         */
        private Pool masterPool = new Pool();

        /**
         * 从节点连接池参数
         */
        private Pool slavePool = new Pool();

        public List<String> getNodes() {
            return nodes;
        }

        public void setNodes(List<String> nodes) {
            this.nodes = nodes;
        }

        public Integer getScanInterval() {
            return scanInterval;
        }

        public void setScanInterval(Integer scanInterval) {
            this.scanInterval = scanInterval;
        }

        public ReadMode getReadMode() {
            return readMode;
        }

        public void setReadMode(ReadMode readMode) {
            this.readMode = readMode;
        }

        public Pool getMasterPool() {
            return masterPool;
        }

        public void setMasterPool(Pool masterPool) {
            this.masterPool = masterPool;
        }

        public Pool getSlavePool() {
            return slavePool;
        }

        public void setSlavePool(Pool slavePool) {
            this.slavePool = slavePool;
        }
    }
}
