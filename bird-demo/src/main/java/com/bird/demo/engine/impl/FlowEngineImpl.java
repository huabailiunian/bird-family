package com.bird.demo.engine.impl;

import com.bird.demo.engine.*;
import com.bird.demo.engine.domain.FlowDefine;

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
