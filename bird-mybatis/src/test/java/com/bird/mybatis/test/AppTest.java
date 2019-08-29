package com.bird.mybatis.test;

import com.bird.core.tools.FileTools;
import com.bird.mybatis.DefaultEngine;
import com.bird.mybatis.define.Database;
import com.bird.mybatis.define.Table;
import com.bird.mybatis.generator.MapperFreemarkerGenerator;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        DefaultEngine engine = new DefaultEngine();
        engine.setBasePackage("com.youly.demo.dal");
        engine.setEntitySuffix("DO");
        Map<String, Object> context = new HashMap<>();
        context.put("engine", engine);

        context.put("model",tables.get(0));

        String process = MapperFreemarkerGenerator.INSTANCE.process(context);

        System.out.println(process);

    }
}
