package com.bird.codegen.impl;

import com.bird.codegen.Annotation;

import java.util.HashMap;
import java.util.Map;

/**
 * @author youly
 * 2019/11/21 16:55
 */
public class AnnotationImpl implements Annotation {

    private static final String DEFAULT_MEMBER_NAME = "value";

    private String className;

    private Map<String, Object> values = new HashMap<>();

    public AnnotationImpl(String className) {
        this.className = className;
    }

    public AnnotationImpl(String className, String value) {
        this.className = className;
        this.setValue(DEFAULT_MEMBER_NAME, value);
    }

    @Override
    public Map<String, Object> getValues() {
        return values;
    }

    @Override
    public Object getValue(String memberName) {
        return values.get(memberName);
    }

    @Override
    public void setValue(String memberName, Object value) {
        values.put(memberName, value);
    }

    @Override
    public void removeValue(String memberName) {
        values.remove(memberName);
    }

    @Override
    public String getClassName() {
        return this.className;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnnotationImpl that = (AnnotationImpl) o;

        if (className != null ? !className.equals(that.className) : that.className != null) return false;
        return values != null ? values.equals(that.values) : that.values == null;
    }

    @Override
    public int hashCode() {
        int result = className != null ? className.hashCode() : 0;
        result = 31 * result + (values != null ? values.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AnnotationImpl{" +
                "className='" + className + '\'' +
                ", values=" + values +
                '}';
    }
}
