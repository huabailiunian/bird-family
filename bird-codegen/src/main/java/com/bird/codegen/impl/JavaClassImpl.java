package com.bird.codegen.impl;

import com.bird.codegen.JavaClass;
import com.bird.codegen.enums.JavaTypeKind;
import com.bird.codegen.enums.Visibility;

import java.util.HashSet;
import java.util.Set;

/**
 * @author youly
 * 2019/11/20 17:28
 */
public class JavaClassImpl extends AbstractJavaType implements JavaClass {

    private String supperClassName;

    private Set<String> interfaces = new HashSet<>();

    private boolean _static = false;

    private boolean _final = false;

    private boolean _abstract = false;

    public JavaClassImpl() {
    }

    public JavaClassImpl(String packageName, String name, String label) {
        super(packageName, name, label, Visibility.PUBLIC, JavaTypeKind.CLASS);
    }

    public JavaClassImpl(String packageName, String name, String label, boolean isStatic, boolean isFinal, boolean isAbstract) {
        super(packageName, name, label, Visibility.PUBLIC, JavaTypeKind.CLASS);
        this._static = isStatic;
        this._final = isFinal;
        this._abstract = isAbstract;
    }

    public JavaClassImpl(String packageName, String name, String label, Visibility visibility, boolean isStatic, boolean isFinal, boolean isAbstract) {
        super(packageName, name, label, visibility, JavaTypeKind.CLASS);
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
    public Set<String> getInterfaces() {
        return this.interfaces;
    }

    @Override
    public void addInterface(String interfaceName) {
        this.interfaces.add(interfaceName);
    }

    @Override
    public void setSupperClassName(String supperClassName) {
        this.supperClassName = supperClassName;
    }

    @Override
    public String getSupperClassName() {
        return this.supperClassName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        JavaClassImpl javaClass = (JavaClassImpl) o;

        if (_static != javaClass._static) return false;
        if (_final != javaClass._final) return false;
        if (_abstract != javaClass._abstract) return false;
        if (supperClassName != null ? !supperClassName.equals(javaClass.supperClassName) : javaClass.supperClassName != null)
            return false;
        return interfaces != null ? interfaces.equals(javaClass.interfaces) : javaClass.interfaces == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (supperClassName != null ? supperClassName.hashCode() : 0);
        result = 31 * result + (interfaces != null ? interfaces.hashCode() : 0);
        result = 31 * result + (_static ? 1 : 0);
        result = 31 * result + (_final ? 1 : 0);
        result = 31 * result + (_abstract ? 1 : 0);
        return result;
    }
}
