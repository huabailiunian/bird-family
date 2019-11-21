package com.bird.codegen.impl;

import com.bird.codegen.JavaInterface;
import com.bird.codegen.ObjectMethod;
import com.bird.codegen.enums.JavaTypeKind;
import com.bird.codegen.enums.Visibility;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author youly
 * 2019/11/20 18:37
 */
public class JavaInterfaceImpl extends AbstractJavaType implements JavaInterface {

    private Set<String> interfaces = new HashSet<>();

    private List<ObjectMethod> methods = new ArrayList<>();

    public JavaInterfaceImpl() {
    }

    public JavaInterfaceImpl(String packageName, String name, String label) {
        super(packageName, name, label, Visibility.PUBLIC, JavaTypeKind.INTERFACE);
    }

    public JavaInterfaceImpl(String packageName, String name, String label, Visibility visibility) {
        super(packageName, name, label, visibility, JavaTypeKind.INTERFACE);
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
    public List<ObjectMethod> getMethods() {
        return this.methods;
    }

    @Override
    public ObjectMethod addMethod(ObjectMethod method) {
        if (method != null) {
            this.methods.add(method);
        }
        return method;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        JavaInterfaceImpl that = (JavaInterfaceImpl) o;

        if (interfaces != null ? !interfaces.equals(that.interfaces) : that.interfaces != null) return false;
        return methods != null ? methods.equals(that.methods) : that.methods == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (interfaces != null ? interfaces.hashCode() : 0);
        result = 31 * result + (methods != null ? methods.hashCode() : 0);
        return result;
    }
}
