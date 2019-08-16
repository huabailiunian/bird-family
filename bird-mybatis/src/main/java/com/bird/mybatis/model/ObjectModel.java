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
