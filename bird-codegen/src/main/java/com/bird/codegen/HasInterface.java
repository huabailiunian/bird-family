package com.bird.codegen;

import java.util.Set;

/**
 * @author youly
 * 2019/11/20 17:25
 */
public interface HasInterface {

    Set<String> getInterfaces();

    void addInterface(String interfaceName);
}
