package com.bird.mybatis.jdbc;

/**
 * @author youly
 * 2019/1/18 10:37
 */
public enum JdbcTypeMapper {

    TINYINT("Integer", "TINYINT"),

    SMALLINT("Integer", "SMALLINT"),

    INT("Integer", "INTEGER"),

    INTEGER("Integer", "INTEGER"),

    BIGINT("Long", "BIGINT"),

    FLOAT("Float", "FLOAT"),

    DOUBLE("Double", "DOUBLE"),

    DECIMAL("java.math.BigDecimal", "DECIMAL"),

    DATE("java.util.Date", "DATE"),

    TIME("java.util.Date", "TIME"),

    DATETIME("java.util.Date", "TIMESTAMP"),

    TIMESTAMP("java.util.Date", "TIMESTAMP"),

    CHAR("String", "CHAR"),

    VARCHAR("String", "VARCHAR"),

    CHARACTER("String", "CHAR"),

    TEXT("String", "CLOB"),

    MEDIUMTEXT("String", "CLOB"),

    LONGTEXT("String", "CLOB"),

    BLOB("byte[]", "BLOB"),

    MEDIUMBLOB("byte[]", "BLOB"),

    LONGBLOB("byte[]", "BLOB");

    private String objectType;
    private String jdbcType;

    JdbcTypeMapper(String objectType, String jdbcType) {
        this.objectType = objectType;
        this.jdbcType = jdbcType;
    }

    public String objectType() {
        return objectType;
    }

    public String jdbcType() {
        return jdbcType;
    }
}
