package com.bird.codegen;

import java.util.List;

/**
 * @author youly
 * 2019/11/21 11:26
 */
public interface ObjectMethod extends HasName, HasClassName, HasVisibility {

    String DEFAULT_METHOD_BAG = List.class.getName();

    String DEFAULT_METHOD_CLASS = void.class.getName();

    boolean isMultiple();

    void setMultiple(boolean multiple);

    String getBag();

    void setBag(String bag);

    boolean isArray();

    boolean isStatic();

    boolean isFinal();

    void setClassName(String className);

    List<ObjectProperty> getParams();

    void addParam(ObjectProperty param);
}
