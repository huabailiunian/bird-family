package com.bird.demo.infrastructure.flowctl;

import com.bird.demo.infrastructure.flowctl.domain.FlowDefine;

/**
 * @author master
 * @date 2020-03-31 09:00
 */
public interface FlowRepository {

    /**
     * 获取定义
     *
     * @param key
     * @return
     */
    FlowDefine get(String key);

}
