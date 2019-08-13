package com.bird.mybatis.definition;

import com.bird.core.consts.BirdConst;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author youly
 * 2019/8/9 16:46
 */
@XStreamAlias("query")
public class Query {

    @XStreamAsAttribute
    private String type;

    @XStreamAsAttribute
    private String name;

    @XStreamAsAttribute
    private String params;

    @XStreamAsAttribute
    private String resultType;

    @XStreamAsAttribute
    private boolean rowMap = false;

    @XStreamAsAttribute
    private boolean array = false;


    public Query() {
    }

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

    public List<String> getParamList() {
        return Arrays.asList(StringUtils.split(params, BirdConst.SEPARATOR_DEFAULT));
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
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
        return "Query{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", params='" + params + '\'' +
                ", resultType='" + resultType + '\'' +
                ", rowMap=" + rowMap +
                ", array=" + array +
                '}';
    }
}
