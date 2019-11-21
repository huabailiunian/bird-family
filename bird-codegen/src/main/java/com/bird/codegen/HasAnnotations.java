package com.bird.codegen;

import java.util.List;

/**
 * @author youly
 * 2019/11/21 16:44
 */
public interface HasAnnotations {

    List<Annotation> getAnnotations();

    Annotation getAnnotation(String className);

    void addAnnotation(Annotation annotation);

    Annotation removeAnnotation(String className);
}
