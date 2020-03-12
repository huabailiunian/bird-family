package com.bird.codegen;

import com.bird.codegen.enums.Visibility;

import java.util.List;

/**
 * 数据对象
 * @author youly
 * 2019/11/20 11:34
 */
public interface DataObject extends JavaClass {

    /**
     * 获取属性字段
     * @return
     */
    List<ObjectProperty> getProperties();

    /**
     * 添加属性字段
     * @param name
     * @param label
     * @param className
     * @return
     */
    ObjectProperty addProperty(String name, String label, String className);

    /**
     * 添加属性字段
     * @param name
     * @param label
     * @param className
     * @param visibility
     * @return
     */
    ObjectProperty addProperty(String name, String label, String className, Visibility visibility);

    ObjectProperty addProperty(String name, String label, String className, boolean multiple);

    ObjectProperty addProperty(String name, String label, String className, boolean multiple, String bag);

    ObjectProperty addProperty(String name, String label, String className, boolean multiple, String bag, Visibility visibility);

    ObjectProperty addProperty(String name, String label, String className, boolean multiple, String bag, Visibility visibility, boolean isStatic, boolean isFinal);

    ObjectProperty addProperty(ObjectProperty property);

    boolean hasProperty(String propertyName);

    ObjectProperty removeProperty(String propertyName);

    ObjectProperty getProperty(String propertyName);
}
