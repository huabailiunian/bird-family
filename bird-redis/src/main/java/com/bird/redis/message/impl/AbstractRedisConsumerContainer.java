package com.bird.redis.message.impl;

import com.bird.redis.message.ConsumerContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;

/**
 * @author youly
 * 2019/7/1 16:18
 */
public abstract class AbstractRedisConsumerContainer implements ConsumerContainer {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private boolean autoStartup = true;

    private volatile boolean running = false;

    private int phase = Ordered.LOWEST_PRECEDENCE;

    private final Object lifecycleMonitor = new Object();

    @Override
    public void start() {
        synchronized (lifecycleMonitor) {
            this.doStart();
        }
    }

    @Override
    public void stop() {
        if (logger.isInfoEnabled()) {
            logger.info("Stop all listeners");
        }
    }

    @Override
    public boolean isRunning() {
        return this.running;
    }

    @Override
    public boolean isAutoStartup() {
        return this.autoStartup;
    }

    @Override
    public void stop(Runnable callback) {
        synchronized (lifecycleMonitor) {
            this.stop();
            this.doStop(callback);
        }
    }

    @Override
    public int getPhase() {
        return this.phase;
    }

    public abstract void doStart();

    public abstract void doStop(Runnable runnable);

    public void setAutoStartup(boolean autoStartup) {
        this.autoStartup = autoStartup;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }
}
