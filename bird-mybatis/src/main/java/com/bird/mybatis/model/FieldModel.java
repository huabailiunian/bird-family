package com.bird.mybatis.model;

/**
 * @author youly
 * 2018/9/29 14:48
 */
public class FieldModel {

    private String name;
    private String type;
    private String display;

    public FieldModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    @Override
    public String toString() {
        return "FieldModel{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", display='" + display + '\'' +
                '}';
    }
}
