package com.bird.codegen;

import com.bird.codegen.enums.Visibility;

/**
 * @author youly
 * 2019/11/19 17:34
 */
public interface HasVisibility {

    boolean isPackagePrivate();

    boolean isPublic();

    boolean isPrivate();

    boolean isProtected();

    Visibility getVisibility();
}
