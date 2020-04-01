package com.bird.codegen.impl;

import com.bird.codegen.DataObject;
import com.bird.codegen.ObjectProperty;
import com.bird.codegen.enums.Visibility;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author youly
 * 2019/11/20 11:41
 */
public class DataObjectImpl extends JavaClassImpl implements DataObject {

    private List<ObjectProperty> properties = new ArrayList<>();

    public DataObjectImpl(String packageName, String name, String label) {
        super(packageName, name, label);
    }

    public DataObjectImpl(String packageName, String name, String label, Visibility visibility) {
        super(packageName, name, label, visibility);
    }

    public DataObjectImpl(String packageName, String name, String label, Visibility visibility, boolean isStatic, boolean isFinal, boolean isAbstract) {
        super(packageName, name, label, visibility, isStatic, isFinal, isAbstract);
    }

    @Override
    public List<ObjectProperty> getProperties() {
        return this.properties;
    }

    @Override
    public ObjectProperty addProperty(String name, String label, String className) {
        return addProperty(name, label, className, Visibility.PRIVATE);
    }

    @Override
    public ObjectProperty addProperty(String name, String label, String className, Visibility visibility) {
        ObjectProperty property = new ObjectPropertyImpl(name, label, className, visibility);
        return addProperty(property);
    }

    @Override
    public ObjectProperty addProperty(String name, String label, String className, boolean multiple) {
        return addProperty(name, label, className, multiple, ObjectProperty.DEFAULT_PROPERTY_BAG);
    }

    @Override
    public ObjectProperty addProperty(String name, String label, String className, boolean multiple, String bag) {
        return addProperty(name, label, className, multiple, bag, Visibility.PRIVATE, Boolean.FALSE, Boolean.FALSE);
    }

    @Override
    public ObjectProperty addProperty(String name, String label, String className, boolean multiple, String bag, Visibility visibility) {
        return addProperty(name, label, className, multiple, bag, visibility, Boolean.FALSE, Boolean.FALSE);
    }

    @Override
    public ObjectProperty addProperty(String name, String label, String className, boolean multiple, String bag, Visibility visibility, boolean isStatic, boolean isFinal) {
        ObjectProperty property = new ObjectPropertyImpl(name, label, className, multiple, bag, visibility, isStatic, isFinal);
        return addProperty(property);
    }

    @Override
    public ObjectProperty addProperty(ObjectProperty property) {
        if (property != null) {
            removeProperty(property.getName());
            properties.add(property);
        }
        return property;
    }

    @Override
    public boolean hasProperty(String propertyName) {
        return this.getProperty(propertyName) != null;
    }

    @Override
    public ObjectProperty removeProperty(String propertyName) {
        ObjectProperty property = this.getProperty(propertyName);
        if (property != null) {
            properties.remove(property);
        }
        return property;
    }

    @Override
    public ObjectProperty getProperty(String propertyName) {
        if (StringUtils.isBlank(propertyName)) {
            return null;
        }
        for (ObjectProperty property : properties) {
            if (propertyName.equals(property.getName())) {
                return property;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        DataObjectImpl that = (DataObjectImpl) o;

        return Objects.equals(properties, that.properties);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (properties != null ? properties.hashCode() : 0);
        return result;
    }
}
