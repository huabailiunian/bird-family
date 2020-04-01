package com.bird.mybatis.define;

import com.bird.core.consts.GlobalConst;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 索引定义类
 *
 * @author youly
 * 2019/4/17 13:31
 */
@XStreamAlias("index")
public class Index {

    @XStreamAsAttribute
    private String name;

    @XStreamAsAttribute
    private String display;

    @XStreamAsAttribute
    private String desc;

    @XStreamAsAttribute
    private String columns;


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

    public String getColumns() {
        return columns;
    }

    public void setColumns(String columns) {
        this.columns = columns;
    }

    public List<String> getColumnList() {
        return Arrays.asList(StringUtils.split(this.columns, GlobalConst.DELIMITER_DEFAULT));
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "name='" + name + '\'' +
                ", display='" + display + '\'' +
                ", desc='" + desc + '\'' +
                ", columns='" + columns + '\'' +
                '}';
    }
}
