package com.bird.demo.infrastructure.drl.element.lhs.condition;

import com.bird.commons.enums.TypeEnum;
import com.bird.demo.infrastructure.drl.element.lhs.HasComplex;
import com.bird.demo.infrastructure.drl.enums.ComplexType;

import java.util.List;

/**
 * @author youly
 * 2019/8/2 10:54
 */
public class ComplexFieldCondition extends SingleFieldCondition implements HasComplex {


    private ComplexType complexType;

    private List<ComplexFieldCondition> conditions;

    public ComplexFieldCondition() {
    }

    public ComplexFieldCondition(ComplexType complexType) {
        this.complexType = complexType;
        this.setComplex(true);
    }

    public ComplexFieldCondition(String fieldName, TypeEnum fieldType, String operation, String value) {
        super(fieldName, fieldType, operation, value);
    }

    public void setComplexType(ComplexType complexType) {
        this.complexType = complexType;
    }

    @Override
    public ComplexType getComplexType() {
        return this.complexType;
    }

    public List<ComplexFieldCondition> getConditions() {
        return conditions;
    }

    public void setConditions(List<ComplexFieldCondition> conditions) {
        this.conditions = conditions;
    }
}
