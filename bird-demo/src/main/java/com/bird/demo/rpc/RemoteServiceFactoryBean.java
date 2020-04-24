package com.bird.demo.rpc;

import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

/**
 * @author master
 * @date 2020-04-22 17:31
 */
public class RemoteServiceFactoryBean implements FactoryBean<Object> {

    private Class<?> type;

    private HttpInvocationHandler httpInvocationHandler;

    @Override
    public Object getObject() throws Exception {
        return Proxy.newProxyInstance(type.getClassLoader(), new Class[]{type}, httpInvocationHandler);
    }

    @Override
    public Class<?> getObjectType() {
        return type;
    }


    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public HttpInvocationHandler getHttpInvocationHandler() {
        return httpInvocationHandler;
    }

    public void setHttpInvocationHandler(HttpInvocationHandler httpInvocationHandler) {
        this.httpInvocationHandler = httpInvocationHandler;
    }
}
