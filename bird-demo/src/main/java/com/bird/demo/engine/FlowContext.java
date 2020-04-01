package com.bird.demo.engine;

/**
 * 上下文
 *
 * @author master
 * @date 2020-03-31 09:00
 */
public interface FlowContext {

    /**
     * 获取请求参数
     * http|dubbo|other
     *
     * @return request
     */
    FlowRequest getRequest();

    /**
     * 获取执行结果
     *
     * @return response
     */
    FlowResponse getResponse();

    /**
     * 获取变量
     *
     * @return val
     */
    Object getVariable();

    /**
     * 添加变量
     *
     * @param key   key
     * @param value val
     */
    void addVariable(String key, Object value);

}
