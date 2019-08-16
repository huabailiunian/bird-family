package com.bird.maven.service;

import com.bird.core.generator.Generator;
import com.bird.core.tools.CollectionTools;
import com.bird.core.tools.FileTools;
import com.bird.mybatis.ObjectModelConvertor;
import com.bird.mybatis.definition.Database;
import com.bird.mybatis.generator.DaoFreemarkerGenerator;
import com.bird.mybatis.generator.MapperFreemarkerGenerator;
import com.bird.mybatis.generator.ObjectFreemarkerGenerator;
import com.bird.mybatis.generator.SQLFreemarkerGenerator;
import com.bird.mybatis.model.Config;
import com.bird.mybatis.model.ObjectModel;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.logging.Log;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
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
        List<ObjectModel> objectList = ObjectModelConvertor.convertor(config, database.getTables());
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

    private void autoWriteAll(Config config, List<ObjectModel> objectList) {
        if (CollectionTools.isEmpty(objectList)) {
            return;
        }
        if (config.isAutoDao()) {
            try {
                Generator generator = DaoFreemarkerGenerator.INSTANCE;
                for (ObjectModel object : objectList) {
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
                Generator generator = ObjectFreemarkerGenerator.INSTANCE;
                for (ObjectModel object : objectList) {
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
                for (ObjectModel object : objectList) {
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
