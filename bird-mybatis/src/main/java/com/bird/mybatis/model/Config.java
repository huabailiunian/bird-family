package com.bird.mybatis.model;


import java.nio.file.Paths;

/**
 * @author youly
 * 2018/9/29 18:09
 */
public class Config {

    private String sourcePath;
    private String resourcesPath;
    private String basePackage;
    private String mapperLocation;
    private String daoSuffix;
    private String entitySuffix;
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

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getDaoPath() {
        return Paths.get(sourcePath, basePackage.replace(".", "/"), "dao").toString();
    }

    public String getEntityPath() {
        return Paths.get(sourcePath, basePackage.replace(".", "/"), "object").toString();
    }

    public String getMapperLocation() {
        return mapperLocation;
    }

    public String getMapperPath() {
        return Paths.get(resourcesPath, mapperLocation).toString();
    }

    public void setMapperLocation(String mapperLocation) {
        this.mapperLocation = mapperLocation;
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

    @Override
    public String toString() {
        return "Config{" +
                "sourcePath='" + sourcePath + '\'' +
                ", resourcesPath='" + resourcesPath + '\'' +
                ", basePackage='" + basePackage + '\'' +
                ", mapperLocation='" + mapperLocation + '\'' +
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
