package com.bird.codegen.engine;

/**
 * @author youly
 * 2019/11/20 11:26
 */
public class Context {

    private Object model;

    public Context() {
    }

    public Context(Object model) {
        this.model = model;
    }

    public Object getModel() {
        return model;
    }

    public void setModel(Object model) {
        this.model = model;
    }
}
