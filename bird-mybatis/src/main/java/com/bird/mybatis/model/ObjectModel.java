package com.bird.mybatis.model;


import java.util.List;

/**
 * @author youly
 * 2018/9/29 14:48
 */
public class ObjectModel {

    private String pkg;
    private String name;
    private String tableName;
    private String display;
    private String daoSuffix;
    private String entitySuffix;

    private FieldModel autoInPK;
    private List<FieldModel> primaryKey;
    private List<FieldModel> fields;

    private List<QueryModel> queries;

    public ObjectModel() {
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
        return pkg + "." + name;
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

    public FieldModel getAutoInPK() {
        return autoInPK;
    }

    public void setAutoInPK(FieldModel autoInPK) {
        this.autoInPK = autoInPK;
    }

    public List<FieldModel> getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(List<FieldModel> primaryKey) {
        this.primaryKey = primaryKey;
    }

    public List<FieldModel> getFields() {
        return fields;
    }

    public void setFields(List<FieldModel> fields) {
        this.fields = fields;
    }

    public List<QueryModel> getQueries() {
        return queries;
    }

    public void setQueries(List<QueryModel> queries) {
        this.queries = queries;
    }

    public String getDaoName() {
        return name + daoSuffix;
    }

    public String getObjectName() {
        return name + entitySuffix;
    }

    public String getMapperName() {
        return name + "Mapper";
    }
}
