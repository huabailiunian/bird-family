package com.bird.mybatis.define;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author youly
 * 2019/1/18 10:37
 */
@XStreamAlias("setting")
public class Setting {

    private String database;

    private String dialect;

    private String charset;

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    @Override
    public String toString() {
        return "Setting{" +
                "database='" + database + '\'' +
                ", dialect='" + dialect + '\'' +
                ", charset='" + charset + '\'' +
                '}';
    }
}
