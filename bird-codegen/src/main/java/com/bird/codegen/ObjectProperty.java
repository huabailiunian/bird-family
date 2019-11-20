package com.bird.codegen;

import java.util.List;

/**
 * @author youly
 * 2019/11/19 18:27
 */
public interface ObjectProperty extends HasName, HasClassName, HasVisibility {

    String DEFAULT_PROPERTY_BAG = List.class.getName();

    boolean isMultiple();

    void setMultiple(boolean multiple);

    boolean isArray();

    boolean isBaseType();

    boolean isPrimitiveType();

    String getBag();

    void setBag(String bag);

    boolean isStatic();

    boolean isFinal();

    void setClassName(String className);
}
