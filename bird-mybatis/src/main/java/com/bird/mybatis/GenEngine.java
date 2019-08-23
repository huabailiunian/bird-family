package com.bird.mybatis;

import com.bird.mybatis.define.Column;

import java.util.List;

/**
 * @author youly
 * 2019/8/23 17:27
 */
public interface GenEngine {

    String getDaoPkg();

    String getEntityPkg();

    /**
     * 实体名生成
     *
     * @param tableName 数据表名
     */
    String entityNameGen(String tableName);

    /**
     * Dao接口名生成
     *
     * @param tableName 数据表名
     */
    String daoNameGen(String tableName);

    /**
     * 属性名生成
     *
     * @param columnName 字段名
     */
    String fieldNameGen(String columnName);

    /**
     * 筛选出主键字段
     *
     * @param columns 所有字段
     */
    List<Column> keys(List<Column> columns);

    String fieldType(String type);

    String jdbcType(String type);
}
