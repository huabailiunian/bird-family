package com.bird.codegen;

import java.util.List;

/**
 * @author youly
 * 2019/11/20 18:34
 */
public interface JavaInterface extends JavaType, HasInterface {

    List<ObjectMethod> getMethods();

    ObjectMethod addMethod(ObjectMethod method);
}
