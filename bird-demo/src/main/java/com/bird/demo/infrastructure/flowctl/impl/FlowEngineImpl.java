package com.bird.demo.infrastructure.flowctl.impl;

import com.bird.demo.infrastructure.flowctl.*;
import com.bird.demo.infrastructure.flowctl.domain.FlowDefine;

/**
 * @author master
 * @date 2020-03-31 15:30
 */
public class FlowEngineImpl implements FlowEngine {

    private FlowRepository flowRepository;

    private HandlerRepository handlerRepository;

    @Override
    public void execute(String key, FlowContext context) {
        FlowDefine flowDefine = flowRepository.get(key);

    }
}
