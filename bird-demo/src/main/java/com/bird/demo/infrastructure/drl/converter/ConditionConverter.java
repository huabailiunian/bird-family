package com.bird.demo.infrastructure.drl.converter;

import com.bird.commons.enums.TypeEnum;
import com.bird.commons.tools.CollectionTools;
import com.bird.demo.infrastructure.drl.IConverter;
import com.bird.demo.infrastructure.drl.IElement;
import com.bird.demo.infrastructure.drl.element.lhs.condition.ComplexFieldCondition;
import com.bird.demo.infrastructure.drl.element.lhs.condition.FieldCondition;
import com.bird.demo.infrastructure.drl.element.lhs.condition.SingleFieldCondition;
import com.bird.demo.infrastructure.drl.enums.ComplexType;
import com.bird.demo.infrastructure.drl.enums.ElementType;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author youly
 * 2019/8/2 11:05
 */
public class ConditionConverter implements IConverter, IElement {


    @Override
    public String process(Object object) {
        FieldCondition condition = (FieldCondition) object;
        if (condition.isComplex()) {
            return processComplex(condition);
        }
        return processSingle(condition);
    }

    private String processSingle(FieldCondition condition) {
        SingleFieldCondition fieldCondition = (SingleFieldCondition) condition;
        String fieldName = fieldCondition.getFieldName();
        String operation = fieldCondition.getOperation();
        String value = fieldCondition.getValue();
        TypeEnum valueType = condition.getValueType();
        switch (valueType) {
            case BYTE:
            case LONG:
            case FLOAT:
            case SHORT:
            case DOUBLE:
            case BOOLEAN:
            case DECIMAL:
            case INTEGER:
                return fieldName + " " + operation + " " + value;
            case STRING:
            case DATE:
            default:
                return fieldName + " " + operation + " \"" + value + "\"";
        }

    }

    private String processComplex(FieldCondition condition) {
        ComplexFieldCondition fieldCondition = (ComplexFieldCondition) condition;
        ComplexType complexType = fieldCondition.getComplexType();
        List<ComplexFieldCondition> conditionList = fieldCondition.getConditions();
        List<String> result = new ArrayList<>(conditionList.size());
        for (FieldCondition var : conditionList) {
            String data = this.process(var);
            if (StringUtils.isNotBlank(data)) {
                result.add(data);
            }
        }
        if (CollectionTools.isEmpty(result)) {
            return null;
        }
        String join = String.join(complexType.operation(), result);
        if (result.size() > 1) {
            join = "(" + join + ")";
        }
        return join;
    }

    @Override
    public ElementType getElementType() {
        return ElementType.PATTERN;
    }
}
