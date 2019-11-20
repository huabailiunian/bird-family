package com.bird.codegen;

/**
 * @author youly
 * 2019/11/20 17:27
 */
public interface JavaClass extends JavaType, HasSupperClass, HasInterface {

    boolean isAbstract();

    boolean isFinal();

    boolean isStatic();
}
