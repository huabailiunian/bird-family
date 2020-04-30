package com.bird.demo.infrastructure.drl.element.lhs.condition;

import com.bird.commons.enums.TypeEnum;
import com.bird.demo.infrastructure.drl.enums.ElementType;

/**
 * @author youly
 * 2019/8/2 10:44
 */
public class SingleFieldCondition implements FieldCondition {

    private String fieldName;
    private TypeEnum fieldType;
    private String operation;
    private String value;

    private boolean complex;

    public SingleFieldCondition() {
    }

    public SingleFieldCondition(String fieldName, TypeEnum fieldType, String operation, String value) {
        this.fieldName = fieldName;
        this.fieldType = fieldType;
        this.operation = operation;
        this.value = value;
        this.complex = false;
    }

    public TypeEnum getFieldType() {
        return fieldType;
    }

    public void setFieldType(TypeEnum fieldType) {
        this.fieldType = fieldType;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public String getOperation() {
        return this.operation;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public boolean isComplex() {
        return this.complex;
    }

    @Override
    public TypeEnum getValueType() {
        return this.fieldType;
    }

    public void setComplex(boolean complex) {
        this.complex = complex;
    }

    @Override
    public ElementType getElementType() {
        return ElementType.CONDITION;
    }
}
