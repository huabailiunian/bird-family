package com.bird.demo.infrastructure.drl.element;

import com.bird.demo.infrastructure.drl.IElement;
import com.bird.demo.infrastructure.drl.enums.ElementType;
import org.apache.commons.lang3.StringUtils;

/**
 * @author youly
 * 2019/7/19 14:08
 */
public class RuleMetaData implements IElement {

    private String metaName;
    private String value;

    public RuleMetaData() {
    }

    public RuleMetaData(String metaName, String value) {
        this.metaName = metaName;
        this.value = value;
    }

    public String getMetaName() {
        return metaName;
    }

    public void setMetaName(String metaName) {
        this.metaName = metaName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "@" + metaName + (StringUtils.isBlank(value) ? "" : "(" + value + ")");
    }

    @Override
    public ElementType getElementType() {
        return ElementType.METADATA;
    }
}
