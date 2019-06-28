package com.bird.mybatis.definition;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * @author youly
 * 2019/1/18 10:34
 */
@XStreamAlias("database")
public class Database {

    private Setting setting;

    @XStreamImplicit
    private List<Table> tables;

    public Setting getSetting() {
        return setting;
    }

    public void setSetting(Setting setting) {
        this.setting = setting;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    @Override
    public String toString() {
        return "Database{" +
                "setting=" + setting +
                ", tables=" + tables +
                '}';
    }
}

