package com.bird.mybatis.test;

import com.bird.core.tools.FileTools;
import com.bird.mybatis.ObjectModelConverter;
import com.bird.mybatis.definition.Database;
import com.bird.mybatis.generator.DaoFreemarkerGenerator;
import com.bird.mybatis.model.Config;
import com.bird.mybatis.model.ObjectModel;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author youly
 * 2019/8/13 11:13
 */
public class GeneratorTest {

    @Test
    public void daoTest() throws IOException {

        InputStream stream = ClassLoader.getSystemResourceAsStream("body.table.xml");
        String data = FileTools.readFile(stream);
        XStream xStream = new XStream();
        xStream.ignoreUnknownElements();
        xStream.addPermission(AnyTypePermission.ANY);
        xStream.setMode(XStream.NO_REFERENCES);
        xStream.processAnnotations(Database.class);

        Database database = (Database) xStream.fromXML(data);
        Config config = new Config();
        config.setBasePackage("com.demo");
        config.setEntitySuffix("");
        List<ObjectModel> models = ObjectModelConverter.converter(config, database.getTables());
        ObjectModel objectModel = models.get(0);
        String process = DaoFreemarkerGenerator.INSTANCE.process(objectModel);

        System.out.println(process);

    }
}
