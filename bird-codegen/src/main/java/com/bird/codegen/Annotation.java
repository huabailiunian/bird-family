package com.bird.codegen;

import java.util.Map;

/**
 * @author youly
 * 2019/11/21 16:47
 */
public interface Annotation extends HasClassName {

    Map<String, Object> getValues();

    Object getValue(String memberName);

    void setValue(String memberName, Object value);

    void removeValue(String memberName);
}
