package com.bird.demo.flowctl;

/**
 * 流程控制引擎
 *
 * @author master
 * @date 2020-03-31 08:59
 */
public interface FlowEngine {

    /**
     * 执行流程
     *
     * @param key     key
     * @param context 上下文
     */
    void execute(String key, FlowContext context);
}
