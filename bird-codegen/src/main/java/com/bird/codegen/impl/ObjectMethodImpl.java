package com.bird.codegen.impl;

import com.bird.codegen.ObjectMethod;
import com.bird.codegen.ObjectProperty;
import com.bird.codegen.enums.Visibility;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author youly
 * 2019/11/21 11:27
 */
public class ObjectMethodImpl implements ObjectMethod {

    private String name;

    private String label;

    private String className;

    private boolean multiple;

    private String bag;

    private Visibility visibility = Visibility.PUBLIC;

    private List<ObjectProperty> params = new ArrayList<>();

    private boolean _static = false;

    private boolean _final = false;

    public ObjectMethodImpl(String name, String label) {
        this.name = name;
        this.label = label;
        this.className = DEFAULT_METHOD_CLASS;
        this.multiple = false;
    }

    public ObjectMethodImpl(String name, String label, String className) {
        this.name = name;
        this.label = label;
        this.className = className;
        this.multiple = false;
    }

    public ObjectMethodImpl(String name, String label, String className, Visibility visibility) {
        this.name = name;
        this.label = label;
        this.className = className;
        this.visibility = visibility;
        this.multiple = false;
    }

    public ObjectMethodImpl(String name, String label, String className, boolean multiple) {
        this.name = name;
        this.label = label;
        this.className = className;
        this.multiple = multiple;
        this.bag = DEFAULT_METHOD_BAG;
    }

    public ObjectMethodImpl(String name, String label, String className, boolean multiple, String bag) {
        this.name = name;
        this.label = label;
        this.className = className;
        this.multiple = multiple;
        this.bag = bag;
    }

    public ObjectMethodImpl(String name, String label, String className, boolean multiple, String bag, Visibility visibility) {
        this.name = name;
        this.label = label;
        this.className = className;
        this.multiple = multiple;
        this.bag = bag;
        this.visibility = visibility;
    }

    public ObjectMethodImpl(String name, String label, String className, Visibility visibility, boolean isStatic, boolean isFinal) {
        this.name = name;
        this.label = label;
        this.className = className;
        this.visibility = visibility;
        this._static = isStatic;
        this._final = isFinal;
    }

    public ObjectMethodImpl(String name, String label, String className, boolean multiple, String bag, Visibility visibility, boolean isStatic, boolean isFinal) {
        this.name = name;
        this.label = label;
        this.className = className;
        this.multiple = multiple;
        this.bag = bag;
        this.visibility = visibility;
        this._static = isStatic;
        this._final = isFinal;
    }

    @Override
    public boolean isMultiple() {
        return this.multiple;
    }

    @Override
    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
        if (!multiple) {
            this.bag = null;
        }
    }

    @Override
    public String getBag() {
        return this.bag;
    }

    @Override
    public void setBag(String bag) {
        this.bag = bag;
    }

    @Override
    public boolean isArray() {
        return this.className.endsWith("[]");
    }

    @Override
    public boolean isStatic() {
        return this._static;
    }

    @Override
    public boolean isFinal() {
        return this._final;
    }

    @Override
    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public List<ObjectProperty> getParams() {
        return this.params;
    }

    @Override
    public void addParam(ObjectProperty param) {
        if (param != null) {
            this.params.add(param);
        }
    }

    @Override
    public String getClassName() {
        return this.className;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public boolean isPackagePrivate() {
        return this.visibility == Visibility.PACKAGE_PRIVATE;
    }

    @Override
    public boolean isPublic() {
        return this.visibility == Visibility.PUBLIC;
    }

    @Override
    public boolean isPrivate() {
        return this.visibility == Visibility.PRIVATE;
    }

    @Override
    public boolean isProtected() {
        return this.visibility == Visibility.PROTECTED;
    }

    @Override
    public Visibility getVisibility() {
        return this.visibility;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ObjectMethodImpl that = (ObjectMethodImpl) o;

        if (multiple != that.multiple) {
            return false;
        }
        if (_static != that._static) {
            return false;
        }
        if (_final != that._final) {
            return false;
        }
        if (!Objects.equals(name, that.name)) {
            return false;
        }
        if (!Objects.equals(label, that.label)) {
            return false;
        }
        if (!Objects.equals(className, that.className)) {
            return false;
        }
        if (!Objects.equals(bag, that.bag)) {
            return false;
        }
        if (visibility != that.visibility) {
            return false;
        }
        return Objects.equals(params, that.params);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (label != null ? label.hashCode() : 0);
        result = 31 * result + (className != null ? className.hashCode() : 0);
        result = 31 * result + (multiple ? 1 : 0);
        result = 31 * result + (bag != null ? bag.hashCode() : 0);
        result = 31 * result + (visibility != null ? visibility.hashCode() : 0);
        result = 31 * result + (params != null ? params.hashCode() : 0);
        result = 31 * result + (_static ? 1 : 0);
        result = 31 * result + (_final ? 1 : 0);
        return result;
    }
}
