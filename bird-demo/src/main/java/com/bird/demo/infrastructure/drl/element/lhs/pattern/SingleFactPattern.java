package com.bird.demo.infrastructure.drl.element.lhs.pattern;

import com.bird.demo.infrastructure.drl.element.lhs.condition.ComplexFieldCondition;
import com.bird.demo.infrastructure.drl.enums.ElementType;

/**
 * @author youly
 * 2019/8/2 14:03
 */
public class SingleFactPattern implements FactPattern {

    private String factType;
    private String bindName;
    private ComplexFieldCondition fieldCondition;
    private boolean group;

    public SingleFactPattern() {
    }

    public SingleFactPattern(String factType, String bindName) {
        this.factType = factType;
        this.bindName = bindName;
        this.group = false;
    }

    public void setFactType(String factType) {
        this.factType = factType;
    }

    public void setBindName(String bindName) {
        this.bindName = bindName;
    }

    public void setGroup(boolean group) {
        this.group = group;
    }

    public ComplexFieldCondition getFieldCondition() {
        return fieldCondition;
    }

    public void setFieldCondition(ComplexFieldCondition fieldCondition) {
        this.fieldCondition = fieldCondition;
    }

    @Override
    public String getFactType() {
        return this.factType;
    }

    @Override
    public String getBindName() {
        return this.bindName;
    }

    @Override
    public boolean isGroup() {
        return this.group;
    }

    @Override
    public ElementType getElementType() {
        return ElementType.PATTERN;
    }
}
