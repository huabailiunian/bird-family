package com.bird.mybatis.definition;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * @author youly
 * 2019/1/18 10:38
 */
@XStreamAlias("table")
public class Table {

    @XStreamAsAttribute
    private String name;

    @XStreamAsAttribute
    private String display;

    @XStreamAsAttribute
    private String desc;

    @XStreamImplicit
    private List<Column> columns;

    @XStreamImplicit
    private List<UniqueKey> uniqueKeys;

    @XStreamImplicit
    private List<Index> indexes;

    private List<Query> queries;

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

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public List<UniqueKey> getUniqueKeys() {
        return uniqueKeys;
    }

    public void setUniqueKeys(List<UniqueKey> uniqueKeys) {
        this.uniqueKeys = uniqueKeys;
    }

    public List<Index> getIndexes() {
        return indexes;
    }

    public void setIndexes(List<Index> indexes) {
        this.indexes = indexes;
    }

    public List<Query> getQueries() {
        return queries;
    }

    public void setQueries(List<Query> queries) {
        this.queries = queries;
    }

    @Override
    public String toString() {
        return "Table{" +
                "name='" + name + '\'' +
                ", display='" + display + '\'' +
                ", desc='" + desc + '\'' +
                ", columns=" + columns +
                ", uniqueKeys=" + uniqueKeys +
                ", indexes=" + indexes +
                ", queries=" + queries +
                '}';
    }
}
