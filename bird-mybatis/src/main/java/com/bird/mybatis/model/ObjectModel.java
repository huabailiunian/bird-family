package com.bird.mybatis.model;


import java.util.List;

/**
 * @author youly
 * 2018/9/29 14:48
 */
public class ObjectModel {

    private String pkg;

    private String name;

    private List<String> imports;

    private List<FieldModel> fields;

    private String className;
    private String fullClassName;
    private String tableName;
    private String display;
    private String daoSuffix;
    private String entitySuffix;
    private String basePackage;

    private FieldModel autoInPK;
    private List<FieldModel> primaryKey;

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

    public String getDaoName() {
        return className + daoSuffix;
    }

    public String getObjectName() {
        return className + entitySuffix;
    }

    public String getMapperName() {
        return className + "Mapper";
    }

    public String getPkg() {
        return pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return this.pkg + "." + name;
    }

    public List<String> getImports() {
        return imports;
    }

    public void setImports(List<String> imports) {
        this.imports = imports;
    }

    public List<FieldModel> getFields() {
        return fields;
    }

    public void setFields(List<FieldModel> fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return "ObjectModel{" +
                "pkg='" + pkg + '\'' +
                ", name='" + name + '\'' +
                ", imports=" + imports +
                ", fields=" + fields +
                '}';
    }
}
