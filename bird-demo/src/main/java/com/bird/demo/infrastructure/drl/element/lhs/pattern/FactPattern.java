package com.bird.demo.infrastructure.drl.element.lhs.pattern;

import com.bird.demo.infrastructure.drl.element.lhs.IPattern;

/**
 * @author youly
 * 2019/8/2 10:35
 */
public interface FactPattern extends IPattern {

    String getFactType();

    String getBindName();


    boolean isGroup();
}
