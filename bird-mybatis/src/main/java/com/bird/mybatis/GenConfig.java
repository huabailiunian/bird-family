package com.bird.mybatis;


import com.bird.commons.constant.Const;
import com.bird.mybatis.define.Column;
import com.bird.mybatis.jdbc.JdbcTypeMapper;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author youly
 * 2018/9/29 18:09
 */
public class GenConfig {

    private String sourcePath;
    private String resourcesPath;
    private String basePackage;
    private String mapperLocation = "mybatis-mapper";
    private String daoDirectory = "dao";
    private String entityDirectory = "object";
    private String daoSuffix = "Dao";
    private String entitySuffix = "";
    private String regex = "_";
    private boolean autoDao;
    private boolean autoEntity;
    private boolean autoMapper;
    private boolean autoSQL;

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public String getResourcesPath() {
        return resourcesPath;
    }

    public void setResourcesPath(String resourcesPath) {
        this.resourcesPath = resourcesPath;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getMapperLocation() {
        return mapperLocation;
    }

    public void setMapperLocation(String mapperLocation) {
        this.mapperLocation = mapperLocation;
    }

    public String getDaoDirectory() {
        return daoDirectory;
    }

    public void setDaoDirectory(String daoDirectory) {
        this.daoDirectory = daoDirectory;
    }

    public String getEntityDirectory() {
        return entityDirectory;
    }

    public void setEntityDirectory(String entityDirectory) {
        this.entityDirectory = entityDirectory;
    }

    public String getDaoSuffix() {
        return daoSuffix;
    }

    public void setDaoSuffix(String daoSuffix) {
        this.daoSuffix = daoSuffix;
    }

    public String getEntitySuffix() {
        return entitySuffix;
    }

    public void setEntitySuffix(String entitySuffix) {
        this.entitySuffix = entitySuffix;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public boolean isAutoDao() {
        return autoDao;
    }

    public void setAutoDao(boolean autoDao) {
        this.autoDao = autoDao;
    }

    public boolean isAutoEntity() {
        return autoEntity;
    }

    public void setAutoEntity(boolean autoEntity) {
        this.autoEntity = autoEntity;
    }

    public boolean isAutoMapper() {
        return autoMapper;
    }

    public void setAutoMapper(boolean autoMapper) {
        this.autoMapper = autoMapper;
    }

    public boolean isAutoSQL() {
        return autoSQL;
    }

    public void setAutoSQL(boolean autoSQL) {
        this.autoSQL = autoSQL;
    }

    public String getDaoPkg() {
        return this.basePackage + "." + daoDirectory;
    }

    public String getEntityPkg() {
        return this.basePackage + "." + entityDirectory;
    }

    public String fieldType(String type) {
        return JdbcTypeMapper.valueOf(type.toUpperCase()).objectType();
    }

    public String jdbcType(String type) {
        return JdbcTypeMapper.valueOf(type.toUpperCase()).jdbcType();
    }

    public List<Column> keys(List<Column> columns) {
        return columns.stream().filter(Column::isPrimaryKey).collect(Collectors.toList());
    }

    public List<Column> queryColGen(List<Column> columns, String params) {
        if (StringUtils.isBlank(params)) {
            return Collections.emptyList();
        }
        List<String> strings = Arrays.asList(StringUtils.split(params, Const.DEFAULT_SEPARATOR));
        return columns.stream().filter(col -> strings.contains(col.getName())).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "GenConfig{" +
                "sourcePath='" + sourcePath + '\'' +
                ", resourcesPath='" + resourcesPath + '\'' +
                ", basePackage='" + basePackage + '\'' +
                ", mapperLocation='" + mapperLocation + '\'' +
                ", daoDirectory='" + daoDirectory + '\'' +
                ", entityDirectory='" + entityDirectory + '\'' +
                ", daoSuffix='" + daoSuffix + '\'' +
                ", entitySuffix='" + entitySuffix + '\'' +
                ", regex='" + regex + '\'' +
                ", autoDao=" + autoDao +
                ", autoEntity=" + autoEntity +
                ", autoMapper=" + autoMapper +
                ", autoSQL=" + autoSQL +
                '}';
    }
}
