package com.bird.demo.infrastructure.drl.element.lhs.condition;

import com.bird.commons.enums.TypeEnum;
import com.bird.demo.infrastructure.drl.element.lhs.ICondition;

/**
 * @author youly
 * 2019/8/2 10:41
 */
public interface FieldCondition extends ICondition {

    boolean isComplex();

    TypeEnum getValueType();

}
