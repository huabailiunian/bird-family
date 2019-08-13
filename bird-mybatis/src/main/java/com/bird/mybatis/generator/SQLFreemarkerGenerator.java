package com.bird.mybatis.generator;

import freemarker.template.Template;

import java.io.IOException;

/**
 * @author youly
 * 2019/1/23 16:42
 */
public class SQLFreemarkerGenerator extends BaseFreemarkerGenerator {

    private static final String TEMPLATE_FILE = "sql.ftl";

    public static final SQLFreemarkerGenerator INSTANCE = new SQLFreemarkerGenerator();

    private SQLFreemarkerGenerator() {
    }

    @Override
    public Template getTemplate() throws IOException {
        return FreemarkerConfiguration.INSTANCE.getTemplate(TEMPLATE_FILE);
    }
}
