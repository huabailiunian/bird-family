package com.bird.demo.infrastructure.drl.element;

import com.bird.demo.infrastructure.drl.IElement;
import com.bird.demo.infrastructure.drl.enums.ElementType;

/**
 * @author youly
 * 2019/7/19 14:09
 */
public class RuleProperty implements IElement {
    public static final String NO_LOOP = "no-loop";
    public static final String SALIENCE = "salience";
    public static final String ENABLED = "enabled";
    public static final String DURATION = "duration";
    public static final String TIMER = "timer";
    public static final String LOCK_ON_ACTIVE = "lock-on-active";
    public static final String AUTO_FOCUS = "auto-focus";
    public static final String CALENDARS = "calendars";
    public static final String DIALECT = "dialect";
    public static final String RULEFLOW_GROUP = "ruleflow-group";

    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        switch (this.name) {
            case NO_LOOP:
            case ENABLED:
            case AUTO_FOCUS:
            case LOCK_ON_ACTIVE:
            default:
                return this.name + " " + this.value;
        }
    }

    @Override
    public ElementType getElementType() {
        return ElementType.PROPERTY;
    }
}
