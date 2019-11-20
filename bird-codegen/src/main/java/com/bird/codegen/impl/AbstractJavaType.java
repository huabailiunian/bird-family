package com.bird.codegen.impl;

import com.bird.codegen.JavaType;
import com.bird.codegen.enums.JavaTypeKind;
import com.bird.codegen.enums.Visibility;
import com.bird.codegen.utils.NamingUtils;

/**
 * @author youly
 * 2019/11/19 17:41
 */
public abstract class AbstractJavaType implements JavaType {

    private String name;

    private String label;

    private String packageName;

    private Visibility visibility;

    private JavaTypeKind typeKind = JavaTypeKind.CLASS;

    public AbstractJavaType() {
    }

    public AbstractJavaType(String name, String label, String packageName) {
        this.name = name;
        this.label = label;
        this.packageName = packageName;
        this.visibility = Visibility.PUBLIC;
    }

    public AbstractJavaType(String name, String label, String packageName, Visibility visibility) {
        this.name = name;
        this.label = label;
        this.packageName = packageName;
        this.visibility = visibility;
    }

    public AbstractJavaType(String name, String label, String packageName, Visibility visibility, JavaTypeKind typeKind) {
        this.name = name;
        this.label = label;
        this.packageName = packageName;
        this.visibility = visibility;
        this.typeKind = typeKind;
    }

    @Override
    public boolean isClass() {
        return typeKind == JavaTypeKind.CLASS;
    }

    @Override
    public boolean isEnum() {
        return typeKind == JavaTypeKind.ENUM;
    }

    @Override
    public boolean isInterface() {
        return typeKind == JavaTypeKind.INTERFACE;
    }

    @Override
    public boolean isAnnotation() {
        return typeKind == JavaTypeKind.ANNOTATION;
    }

    @Override
    public JavaTypeKind getTypeKind() {
        return this.typeKind;
    }

    @Override
    public String getClassName() {
        return NamingUtils.createQualifiedName(this.packageName, this.name);
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
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public String getPackageName() {
        return this.packageName;
    }

    @Override
    public boolean isPackagePrivate() {
        return visibility == Visibility.PACKAGE_PRIVATE;
    }

    @Override
    public boolean isPublic() {
        return visibility == Visibility.PUBLIC;
    }

    @Override
    public boolean isPrivate() {
        return visibility == Visibility.PRIVATE;
    }

    @Override
    public boolean isProtected() {
        return visibility == Visibility.PROTECTED;
    }

    @Override
    public Visibility getVisibility() {
        return this.visibility;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractJavaType that = (AbstractJavaType) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (label != null ? !label.equals(that.label) : that.label != null) return false;
        if (packageName != null ? !packageName.equals(that.packageName) : that.packageName != null) return false;
        if (visibility != that.visibility) return false;
        return typeKind == that.typeKind;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (label != null ? label.hashCode() : 0);
        result = 31 * result + (packageName != null ? packageName.hashCode() : 0);
        result = 31 * result + (visibility != null ? visibility.hashCode() : 0);
        result = 31 * result + (typeKind != null ? typeKind.hashCode() : 0);
        return result;
    }
}
