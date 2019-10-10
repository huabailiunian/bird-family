package com.bird.mybatis.define;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * @author youly
 * 2019/1/18 10:37
 */
@XStreamAlias("column")
public class Column {

    @XStreamAsAttribute
    private String name;

    @XStreamAsAttribute
    private String display;

    @XStreamAsAttribute
    private String desc;

    @XStreamAsAttribute
    private String type;

    @XStreamAsAttribute
    private Integer length;

    @XStreamAsAttribute
    private Integer decimal;

    @XStreamAsAttribute
    @XStreamAlias("default")
    private String defaultValue;

    @XStreamAsAttribute
    private boolean allowNull;

    @XStreamAsAttribute
    private boolean unsigned;

    @XStreamAsAttribute
    private boolean primaryKey;

    @XStreamAsAttribute
    private boolean autoIncrement;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getDecimal() {
        return decimal;
    }

    public void setDecimal(Integer decimal) {
        this.decimal = decimal;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public boolean isAllowNull() {
        return allowNull;
    }

    public void setAllowNull(boolean allowNull) {
        this.allowNull = allowNull;
    }

    public boolean isUnsigned() {
        return unsigned;
    }

    public void setUnsigned(boolean unsigned) {
        this.unsigned = unsigned;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public boolean isAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    @Override
    public String toString() {
        return "Column{" +
                "name='" + name + '\'' +
                ", display='" + display + '\'' +
                ", desc='" + desc + '\'' +
                ", type='" + type + '\'' +
                ", length=" + length +
                ", decimal=" + decimal +
                ", defaultValue='" + defaultValue + '\'' +
                ", allowNull=" + allowNull +
                ", unsigned=" + unsigned +
                ", primaryKey=" + primaryKey +
                ", autoIncrement=" + autoIncrement +
                '}';
    }
}
