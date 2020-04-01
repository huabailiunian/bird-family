package com.bird.demo.engine;

/**
 * @author master
 * @date 2020-03-31 09:00
 */
public interface HandlerRepository {

    /**
     * @param key
     * @return
     */
    Handler get(String key);
}
