package com.bird.maven.entity;


import java.util.List;

/**
 * @author youly
 * 2018/9/29 14:48
 */
public class DataObject {

    private String className;
    private String fullClassName;
    private String tableName;
    private String display;
    private String daoSuffix;
    private String entitySuffix;
    private String basePackage;

    private DataField autoInPK;
    private List<DataField> primaryKey;
    private List<DataField> fields;

    public DataObject() {
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getFullClassName() {
        return fullClassName;
    }

    public void setFullClassName(String fullClassName) {
        this.fullClassName = fullClassName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getDaoSuffix() {
        return daoSuffix;
    }

    public void setDaoSuffix(String daoSuffix) {
        this.daoSuffix = daoSuffix;
    }

    public String getEntitySuffix() {
        return entitySuffix;
    }

    public void setEntitySuffix(String entitySuffix) {
        this.entitySuffix = entitySuffix;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public DataField getAutoInPK() {
        return autoInPK;
    }

    public void setAutoInPK(DataField autoInPK) {
        this.autoInPK = autoInPK;
    }

    public List<DataField> getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(List<DataField> primaryKey) {
        this.primaryKey = primaryKey;
    }

    public List<DataField> getFields() {
        return fields;
    }

    public void setFields(List<DataField> fields) {
        this.fields = fields;
    }

    public String getDaoName() {
        return className + daoSuffix;
    }

    public String getObjectName() {
        return className + entitySuffix;
    }

    public String getMapperName() {
        return className + "Mapper";
    }

    @Override
    public String toString() {
        return "DataObject{" +
                "className='" + className + '\'' +
                ", fullClassName='" + fullClassName + '\'' +
                ", tableName='" + tableName + '\'' +
                ", display='" + display + '\'' +
                ", daoSuffix='" + daoSuffix + '\'' +
                ", entitySuffix='" + entitySuffix + '\'' +
                ", basePackage='" + basePackage + '\'' +
                ", autoInPK=" + autoInPK +
                ", primaryKey=" + primaryKey +
                ", fields=" + fields +
                '}';
    }
}
