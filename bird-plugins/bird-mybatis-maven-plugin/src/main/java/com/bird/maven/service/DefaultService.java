package com.bird.maven.service;

import com.bird.core.generator.Generator;
import com.bird.core.tools.CollectionTools;
import com.bird.core.tools.FileTools;
import com.bird.core.tools.StringTools;
import com.bird.maven.entity.Config;
import com.bird.maven.entity.DataField;
import com.bird.maven.entity.DataObject;
import com.bird.maven.generator.DaoFreemarkerGenerator;
import com.bird.maven.generator.EntityFreemarkerGenerator;
import com.bird.maven.generator.MapperFreemarkerGenerator;
import com.bird.maven.generator.SQLFreemarkerGenerator;
import com.bird.mybatis.definition.Column;
import com.bird.mybatis.definition.Database;
import com.bird.mybatis.definition.Table;
import com.bird.mybatis.jdbc.JdbcTypeMapper;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.logging.Log;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author youly
 * 2019/1/18 16:26
 */
public class DefaultService implements GenService {

    private Log logger;

    public DefaultService() {
    }

    @Override
    public void execution(Config config, String filePath) {
        List<File> files = parseFile(filePath);
        if (files.isEmpty()) {
            logger.info("file list is empty. path:" + filePath);
            return;
        }
        XStream stream = new XStream();
        stream.ignoreUnknownElements();
        stream.addPermission(AnyTypePermission.ANY);
        stream.setMode(XStream.NO_REFERENCES);
        stream.processAnnotations(Database.class);

        files.forEach(file -> {
            logger.info("read file:" + file.getAbsolutePath());
            Database database = (Database) stream.fromXML(file);
            resolveDatabase(config, database);
        });

    }

    /**
     * 处理数据生成文件
     *
     * @param config   配置
     * @param database 原始数据
     */
    private void resolveDatabase(Config config, Database database) {
        List<DataObject> objectList = genObject(config, database);
        autoWriteAll(config, objectList);
        if (config.isAutoSQL()) {
            autoWriteSQL(config, database);
        }
    }

    private void autoWriteSQL(Config config, Database database) {
        try {
            Generator generator = SQLFreemarkerGenerator.INSTANCE;
            String path = config.getResourcesPath();
            String tmpName = database.getSetting().getDatabase();
            String name = StringUtils.isNotBlank(tmpName) ? (tmpName + ".sql") : "auto.sql";
            String sql = generator.process(database);
            writeFile(path, name, sql);
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
    }

    private void autoWriteAll(Config config, List<DataObject> objectList) {
        if (CollectionTools.isEmpty(objectList)) {
            return;
        }
        if (config.isAutoDao()) {
            try {
                Generator generator = DaoFreemarkerGenerator.INSTANCE;
                for (DataObject object : objectList) {
                    String daoPath = config.getDaoPath();
                    String daoName = object.getDaoName() + ".java";
                    String data = generator.process(object);
                    writeFile(daoPath, daoName, data);
                }
            } catch (Exception e) {
                logger.warn(e.getMessage());
            }
        }
        if (config.isAutoEntity()) {
            try {
                Generator generator = EntityFreemarkerGenerator.INSTANCE;
                for (DataObject object : objectList) {
                    String entityPath = config.getEntityPath();
                    String name = object.getObjectName() + ".java";
                    String data = generator.process(object);
                    writeFile(entityPath, name, data);
                }
            } catch (Exception e) {
                logger.warn(e.getMessage());
            }
        }
        if (config.isAutoMapper()) {
            try {
                Generator generator = MapperFreemarkerGenerator.INSTANCE;
                for (DataObject object : objectList) {
                    String mapperPath = config.getMapperPath();
                    String mapperName = object.getMapperName() + ".xml";
                    String data = generator.process(object);
                    writeFile(mapperPath, mapperName, data);
                }
            } catch (Exception e) {
                logger.warn(e.getMessage());
            }
        }
    }

    private void writeFile(String path, String name, String data) {
        String filePath = Paths.get(path, name).toString();
        try {
            File file = new File(filePath);
            FileTools.forceMkdir(file.getParentFile());
            FileTools.writeFile(file, data, false);
            logger.info("write file:" + filePath);
        } catch (IOException e) {
            logger.warn("write file error:" + e.getMessage());
        }
    }

    private List<DataObject> genObject(Config config, Database database) {
        List<Table> tables = database.getTables();
        if (CollectionTools.isNotEmpty(tables)) {
            List<DataObject> result = new ArrayList<>(tables.size());
            tables.forEach(table -> {
                DataObject object = new DataObject();
                object.setTableName(table.getName());
                object.setDisplay(table.getDisplay());
                object.setClassName(StringTools.camelCase(table.getName(), config.getRegex()));
                object.setDaoSuffix(config.getDaoSuffix());
                object.setEntitySuffix(config.getEntitySuffix());
                object.setBasePackage(config.getBasePackage());
                object.setPrimaryKey(new ArrayList<>());
                object.setFields(new ArrayList<>());
                genField(object, config.getRegex(), table.getColumns());
                result.add(object);
            });
            return result;
        }
        return Collections.emptyList();
    }

    private void genField(DataObject object, String regex, List<Column> columns) {
        if (CollectionTools.isNotEmpty(columns)) {
            columns.forEach(column -> {
                DataField field = new DataField();
                String type = column.getType();
                field.setFieldType(JdbcTypeMapper.valueOf(type.toUpperCase()).objectType());
                field.setJdbcType(JdbcTypeMapper.valueOf(type.toUpperCase()).jdbcType());
                field.setFieldName(StringTools.lowerCaseFirst(StringTools.camelCase(column.getName().trim(), regex)));
                field.setColumnName(column.getName().trim());
                field.setDesc(column.getDesc());
                field.setLength(column.getLength());
                field.setDisplay(column.getDisplay());
                if (column.isAutoIncrement()) {
                    field.setAutoIncrement(true);
                    field.setPrimaryKey(true);
                    object.setAutoInPK(field);
                }
                if (column.isPrimaryKey()) {
                    field.setPrimaryKey(true);
                    object.getPrimaryKey().add(field);
                }
                object.getFields().add(field);
            });
        }
    }

    private List<File> parseFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return Collections.emptyList();
        }
        if (file.isFile()) {
            return file.getName().endsWith("table.xml") ? Collections.singletonList(file) : Collections.emptyList();
        }
        File[] files = file.listFiles((dir, name) -> name.endsWith("table.xml"));
        return ArrayUtils.isEmpty(files) ? Collections.emptyList() : Arrays.asList(files);
    }

    @Override
    public void setLogger(Log logger) {
        this.logger = logger;
    }

    public Log getLogger() {
        return logger;
    }
}
