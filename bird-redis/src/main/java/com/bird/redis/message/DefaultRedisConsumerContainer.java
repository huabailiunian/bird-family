package com.bird.redis.message;

import com.bird.core.tools.CollectionTools;
import com.bird.redis.client.RedisClientWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.scheduling.SchedulingAwareRunnable;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author youly
 * 2019/7/1 16:34
 */
public class DefaultRedisConsumerContainer extends AbstractRedisConsumerContainer {

    private RedisClientWrapper redisClientWrapper;

    /**
     * 消息超时时间 单位：毫秒
     */
    private long timeout = 10000L;

    private List<RedisConsumer> consumers;
    private int instanceNum = 1;

    private AsyncListenableTaskExecutor listenerTaskExecutor;
    private List<ListenableFuture> futures = new ArrayList<>();
    private boolean interruptIfRunning = false;

    public DefaultRedisConsumerContainer(RedisClientWrapper redisClientWrapper) {
        this.redisClientWrapper = redisClientWrapper;
    }

    @Override
    public void doStart() {
        if (this.isRunning()) {
            return;
        }
        this.setRunning(true);
        if (CollectionTools.isNotEmpty(consumers)) {
            //启动所有的监听器
            consumers.forEach(consumer -> {
                for (int i = 0; i < instanceNum; i++) {
                    futures.add(listenerTaskExecutor.submitListenable(new ListenerConsumer(consumer)));
                }
            });
        }
    }

    @Override
    public void doStop(Runnable runnable) {
        if (isRunning()) {
            this.setRunning(false);
            futures.forEach(future -> future.cancel(interruptIfRunning));
            if (logger.isInfoEnabled()) {
                logger.info("Stop all listeners count[{}]", futures.size());
            }
        }
        if (null != runnable) {
            runnable.run();
        }
    }

    @Override
    public Object poll(String queue) throws InterruptedException {
        return redisClientWrapper.getBlockingQueue(queue).poll(this.timeout, TimeUnit.MILLISECONDS);
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public List<RedisConsumer> getConsumers() {
        return consumers;
    }

    public void setConsumers(List<RedisConsumer> consumers) {
        this.consumers = consumers;
    }

    public int getInstanceNum() {
        return instanceNum;
    }

    public void setInstanceNum(int instanceNum) {
        this.instanceNum = instanceNum;
    }

    public AsyncListenableTaskExecutor getListenerTaskExecutor() {
        return listenerTaskExecutor;
    }

    public void setListenerTaskExecutor(AsyncListenableTaskExecutor listenerTaskExecutor) {
        this.listenerTaskExecutor = listenerTaskExecutor;
    }

    public boolean isInterruptIfRunning() {
        return interruptIfRunning;
    }

    public void setInterruptIfRunning(boolean interruptIfRunning) {
        this.interruptIfRunning = interruptIfRunning;
    }

    @SuppressWarnings("unchecked")
    private final class ListenerConsumer implements SchedulingAwareRunnable {

        private RedisConsumer consumer;

        ListenerConsumer(RedisConsumer consumer) {
            this.consumer = consumer;
        }

        @Override
        public boolean isLongLived() {
            return true;
        }

        @Override
        public void run() {
            String queue = consumer.getQueue();
            String className = consumer.getClass().getSimpleName();
            if (StringUtils.isBlank(queue)) {
                if (logger.isWarnEnabled()) {
                    logger.warn("消费者[{}]未指定消费队列", className);
                }
            } else {
                if (logger.isInfoEnabled()) {
                    logger.info("消费者[{}]实例消费 Redis MQ queue [{}]", className, queue);
                }
                while (isRunning()) {
                    try {
                        Object message = poll(queue);
                        if (null == message) {
                            continue;
                        }
                        if (logger.isDebugEnabled()) {
                            logger.debug("消费者从[{}]收到信息 [{}]", queue, message.toString());
                        }
                        try {
                            consumer.onMessage(queue, message);
                        } catch (Exception e) {
                            if (logger.isWarnEnabled()) {
                                logger.warn("消费者[{}]处理 Message[{}] 失败", className, message.toString(), e);
                            }
                        }
                    } catch (InterruptedException e) {
                        logger.error("消费者[{}]消费异常中断", className, e);
                    }
                }
            }
        }
    }
}
