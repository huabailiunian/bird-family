package com.bird.codegen;

import java.util.Set;

/**
 * @author youly
 * 2019/11/20 17:25
 */
public interface HasInterface {

    /**
     * 获取所有接口
     *
     * @return
     */
    Set<String> getInterfaces();

    /**
     * 添加接口
     *
     * @param interfaceName
     */
    void addInterface(String interfaceName);
}
