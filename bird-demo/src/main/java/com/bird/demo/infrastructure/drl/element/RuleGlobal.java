package com.bird.demo.infrastructure.drl.element;

import com.bird.demo.infrastructure.drl.IElement;
import com.bird.demo.infrastructure.drl.enums.ElementType;

/**
 * @author youly
 * 2019/7/19 14:58
 */
public class RuleGlobal implements IElement {

    /**
     * Class type
     */
    private String type;

    /**
     * 别名
     */
    private String alias;

    public RuleGlobal() {
    }

    public RuleGlobal(String type, String alias) {
        this.type = type;
        this.alias = alias;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public String toString() {
        return "global " + this.type + " " + this.alias + ";\n";
    }

    @Override
    public ElementType getElementType() {
        return ElementType.GLOBAL;
    }
}
