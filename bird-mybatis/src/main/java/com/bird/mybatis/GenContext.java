package com.bird.mybatis;

/**
 * @author youly
 * 2019/8/29 16:03
 */
public class GenContext {

    private GenConfig config;

    private Object model;

    public GenConfig getConfig() {
        return config;
    }

    public void setConfig(GenConfig engine) {
        this.config = engine;
    }

    public Object getModel() {
        return model;
    }

    public void setModel(Object model) {
        this.model = model;
    }
}
