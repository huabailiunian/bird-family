package com.bird.mybatis.model;

import java.util.List;

/**
 * @author youly
 * 2019/8/13 11:20
 */
public class QueryModel {

    private String type;

    private String name;

    private String paramType;

    private List<String> params;

    private String resultType;

    private boolean rowMap = false;

    private boolean array = false;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public List<String> getParams() {
        return params;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public boolean isRowMap() {
        return rowMap;
    }

    public void setRowMap(boolean rowMap) {
        this.rowMap = rowMap;
    }

    public boolean isArray() {
        return array;
    }

    public void setArray(boolean array) {
        this.array = array;
    }

    @Override
    public String toString() {
        return "QueryModel{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", paramType='" + paramType + '\'' +
                ", params=" + params +
                ", resultType='" + resultType + '\'' +
                ", rowMap=" + rowMap +
                ", array=" + array +
                '}';
    }
}
