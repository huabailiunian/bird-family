package com.bird.mybatis.test;

import com.bird.codegen.DataObject;
import com.bird.codegen.engine.CodeGenEngine;
import com.bird.codegen.engine.CodeGenEngineImpl;
import com.bird.codegen.engine.Context;
import com.bird.codegen.exception.CodeGenException;
import com.bird.codegen.impl.DataObjectImpl;
import com.bird.commons.tools.FileTools;
import com.bird.mybatis.define.Column;
import com.bird.mybatis.define.Database;
import com.bird.mybatis.define.Table;
import com.bird.mybatis.jdbc.JdbcTypeMapper;
import com.google.common.base.CaseFormat;
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
    public void daoTest() throws IOException, CodeGenException {

        InputStream stream = ClassLoader.getSystemResourceAsStream("body.table.xml");
        String data = FileTools.readFile(stream);
        XStream xStream = new XStream();
        xStream.ignoreUnknownElements();
        xStream.addPermission(AnyTypePermission.ANY);
        xStream.setMode(XStream.NO_REFERENCES);
        xStream.processAnnotations(Database.class);

        Database database = (Database) xStream.fromXML(data);
        List<Table> tables = database.getTables();
        Table table = tables.get(0);
        String className = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, table.getName());
        List<Column> columns = table.getColumns();
        DataObject dataObject = new DataObjectImpl("com.demo", className, table.getDisplay());
        for (Column column : columns) {
            String colName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, column.getName());
            String objectType = JdbcTypeMapper.valueOf(column.getType().toUpperCase()).objectType();
            dataObject.addProperty(colName, column.getDisplay(), objectType);
        }

        Context context = new Context(dataObject);
        CodeGenEngine engine = new CodeGenEngineImpl();
        String generator = engine.generator(context, CodeGenEngine.DEFAULT_TEMPLATE_POJO);
        System.out.println(generator);

    }
}
