package com.bird.codegen;

import java.util.Map;

/**
 * @author youly
 * 2019/11/21 16:47
 */
public interface Annotation extends HasClassName {

    /**
     * 获取所有
     *
     * @return
     */
    Map<String, Object> getValues();

    /**
     * 根据名称获取
     *
     * @param memberName
     * @return
     */
    Object getValue(String memberName);

    /**
     * set
     *
     * @param memberName
     * @param value
     */
    void setValue(String memberName, Object value);

    /**
     * 删除
     *
     * @param memberName
     */
    void removeValue(String memberName);
}
