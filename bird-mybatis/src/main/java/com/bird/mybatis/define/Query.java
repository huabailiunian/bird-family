package com.bird.mybatis.define;

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
    private String name;

    @XStreamAsAttribute
    private String columns;

    @XStreamAsAttribute
    private String params;

    @XStreamAsAttribute
    private String resultType;

    @XStreamAsAttribute
    private boolean useRowMap = false;

    @XStreamAsAttribute
    private boolean array = false;

    @XStreamAsAttribute
    private boolean useAuto = false;

    @XStreamAsAttribute
    private String extSql;

    public Query() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColumns() {
        return columns;
    }

    public void setColumns(String columns) {
        this.columns = columns;
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

    public boolean isUseRowMap() {
        return useRowMap;
    }

    public void setUseRowMap(boolean useRowMap) {
        this.useRowMap = useRowMap;
    }

    public boolean isArray() {
        return array;
    }

    public void setArray(boolean array) {
        this.array = array;
    }

    public boolean isUseAuto() {
        return useAuto;
    }

    public void setUseAuto(boolean useAuto) {
        this.useAuto = useAuto;
    }

    public String getExtSql() {
        return extSql;
    }

    public void setExtSql(String extSql) {
        this.extSql = extSql;
    }

    @Override
    public String toString() {
        return "Query{" +
                "name='" + name + '\'' +
                ", columns='" + columns + '\'' +
                ", params='" + params + '\'' +
                ", resultType='" + resultType + '\'' +
                ", useRowMap='" + useRowMap + '\'' +
                ", array=" + array +
                ", useAuto=" + useAuto +
                ", extSql=" + extSql +
                '}';
    }
}
