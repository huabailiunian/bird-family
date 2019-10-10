package com.bird.mybatis;

/**
 * @author youly
 * 2019/8/29 16:03
 */
public class GenContext {

    private GenEngine engine;

    private Object model;

    public GenEngine getEngine() {
        return engine;
    }

    public void setEngine(GenEngine engine) {
        this.engine = engine;
    }

    public Object getModel() {
        return model;
    }

    public void setModel(Object model) {
        this.model = model;
    }
}
