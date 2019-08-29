package com.bird.mybatis.test;

import com.bird.core.tools.FileTools;
import com.bird.mybatis.GenContext;
import com.bird.mybatis.GenEngine;
import com.bird.mybatis.define.Database;
import com.bird.mybatis.define.Table;
import com.bird.mybatis.generator.SQLFreemarkerGenerator;
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
public class AppTest {

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
        List<Table> tables = database.getTables();
        GenEngine engine = new GenEngine();
        engine.setBasePackage("com.youly.demo.dal");
        engine.setEntitySuffix("DO");
        GenContext context = new GenContext();
        context.setEngine(engine);

        context.setModel(tables);

        String process = SQLFreemarkerGenerator.INSTANCE.process(context);

        System.out.println(process);

    }
}
