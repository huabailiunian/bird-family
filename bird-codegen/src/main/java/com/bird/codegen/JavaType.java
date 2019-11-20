package com.bird.codegen;

import com.bird.codegen.enums.JavaTypeKind;

/**
 * @author youly
 * 2019/11/19 17:36
 */
public interface JavaType extends HasName, HasClassName, HasPackageName, HasVisibility {

    boolean isClass();

    boolean isEnum();

    boolean isInterface();

    boolean isAnnotation();

    JavaTypeKind getTypeKind();
}
