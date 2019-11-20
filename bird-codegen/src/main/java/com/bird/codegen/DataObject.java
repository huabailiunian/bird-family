package com.bird.codegen;

import com.bird.codegen.enums.Visibility;

import java.util.List;

/**
 * @author youly
 * 2019/11/20 11:34
 */
public interface DataObject extends JavaClass {

    List<ObjectProperty> getProperties();

    ObjectProperty addProperty(String name, String label, String className);

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
