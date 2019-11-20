package com.bird.codegen.impl;

import com.bird.codegen.DataObject;
import com.bird.codegen.ObjectProperty;
import com.bird.codegen.enums.JavaTypeKind;
import com.bird.codegen.enums.Visibility;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author youly
 * 2019/11/20 11:41
 */
public class DataObjectImpl extends AbstractJavaType implements DataObject {

    private List<ObjectProperty> properties = new ArrayList<>();

    private boolean _static = false;
    private boolean _final = false;
    private boolean _abstract = false;

    public DataObjectImpl(String name, String label, String packageName) {
        super(name, label, packageName, Visibility.PUBLIC);
    }

    public DataObjectImpl(String name, String label, String packageName, boolean isStatic, boolean isFinal, boolean isAbstract) {
        super(name, label, packageName, Visibility.PUBLIC);
        this._static = isStatic;
        this._final = isFinal;
        this._abstract = isAbstract;
    }

    public DataObjectImpl(String name, String label, String packageName, Visibility visibility) {
        super(name, label, packageName, visibility);
    }

    public DataObjectImpl(String name, String label, String packageName, Visibility visibility, JavaTypeKind typeKind) {
        super(name, label, packageName, visibility, typeKind);
    }

    public DataObjectImpl(String name, String label, String packageName, Visibility visibility, boolean isStatic, boolean isFinal, boolean isAbstract) {
        super(name, label, packageName, visibility, JavaTypeKind.CLASS);
        this._static = isStatic;
        this._final = isFinal;
        this._abstract = isAbstract;
    }

    public DataObjectImpl(String name, String label, String packageName, Visibility visibility, JavaTypeKind typeKind, boolean isStatic, boolean isFinal, boolean isAbstract) {
        super(name, label, packageName, visibility, typeKind);
        this._static = isStatic;
        this._final = isFinal;
        this._abstract = isAbstract;
    }

    @Override
    public boolean isAbstract() {
        return this._abstract;
    }

    @Override
    public boolean isFinal() {
        return this._final;
    }

    @Override
    public boolean isStatic() {
        return this._static;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        DataObjectImpl that = (DataObjectImpl) o;

        if (_static != that._static) return false;
        if (_final != that._final) return false;
        if (_abstract != that._abstract) return false;
        return properties != null ? properties.equals(that.properties) : that.properties == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (properties != null ? properties.hashCode() : 0);
        result = 31 * result + (_static ? 1 : 0);
        result = 31 * result + (_final ? 1 : 0);
        result = 31 * result + (_abstract ? 1 : 0);
        return result;
    }
}
