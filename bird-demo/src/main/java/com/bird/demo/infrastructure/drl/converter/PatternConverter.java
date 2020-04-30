package com.bird.demo.infrastructure.drl.converter;

import com.bird.commons.tools.StringTools;
import com.bird.demo.infrastructure.drl.IConverter;
import com.bird.demo.infrastructure.drl.element.lhs.condition.ComplexFieldCondition;
import com.bird.demo.infrastructure.drl.element.lhs.pattern.FactPattern;
import com.bird.demo.infrastructure.drl.element.lhs.pattern.GroupFactPattern;
import com.bird.demo.infrastructure.drl.element.lhs.pattern.SingleFactPattern;
import org.apache.commons.lang3.StringUtils;

/**
 * @author youly
 * 2019/8/2 14:25
 */
public class PatternConverter implements IConverter {

    private static final IConverter conditionConverter = new ConditionConverter();

    @Override
    public String process(Object object) {
        FactPattern factPattern = (FactPattern) object;
        if (factPattern.isGroup()) {
            return processGroup(factPattern);
        }
        return processSingle(factPattern);
    }

    private String processSingle(FactPattern factPattern) {
        SingleFactPattern pattern = (SingleFactPattern) factPattern;
        ComplexFieldCondition fieldCondition = pattern.getFieldCondition();
        String condition = conditionConverter.process(fieldCondition);
        String bindName = pattern.getBindName();
        if (StringUtils.isBlank(bindName)) {
            bindName = StringTools.lowerCaseFirst(pattern.getFactType());
        }
        return "$" + bindName + ":" + pattern.getFactType() + "(" + condition + ")";
    }

    private String processGroup(FactPattern factPattern) {
        GroupFactPattern pattern = (GroupFactPattern) factPattern;
        switch (pattern.getGroupType()) {
            case NOT_ALL:
            case NOT_ANY:
            default:
                return null;
        }
    }
}
