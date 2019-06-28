package com.bird.maven.entity;

/**
 * @author youly
 * 2018/9/29 14:48
 */
public class DataField {

    private String fieldName;
    private String fieldType;
    private String columnName;
    private String jdbcType;
    private String display;
    private Integer length;
    private Integer decimal;
    private String desc;
    private boolean allowNull;
    private boolean unsigned;
    private boolean primaryKey;
    private boolean autoIncrement;

    public DataField() {
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
        return "DataField{" +
                "fieldName='" + fieldName + '\'' +
                ", fieldType='" + fieldType + '\'' +
                ", columnName='" + columnName + '\'' +
                ", jdbcType='" + jdbcType + '\'' +
                ", display='" + display + '\'' +
                ", length=" + length +
                ", decimal=" + decimal +
                ", desc='" + desc + '\'' +
                ", allowNull=" + allowNull +
                ", unsigned=" + unsigned +
                ", primaryKey=" + primaryKey +
                ", autoIncrement=" + autoIncrement +
                '}';
    }
}
