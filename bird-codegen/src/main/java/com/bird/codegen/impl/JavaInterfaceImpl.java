package com.bird.codegen.impl;

import com.bird.codegen.JavaInterface;
import com.bird.codegen.enums.JavaTypeKind;
import com.bird.codegen.enums.Visibility;

import java.util.HashSet;
import java.util.Set;

/**
 * @author youly
 * 2019/11/20 18:37
 */
public class JavaInterfaceImpl extends AbstractJavaType implements JavaInterface {

    private Set<String> interfaces = new HashSet<>();

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        JavaInterfaceImpl that = (JavaInterfaceImpl) o;

        return interfaces != null ? interfaces.equals(that.interfaces) : that.interfaces == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (interfaces != null ? interfaces.hashCode() : 0);
        return result;
    }
}
