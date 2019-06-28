package com.bird.maven.generator;

import freemarker.template.Template;

import java.io.IOException;

/**
 * @author youly
 * @version 1.0
 * 2018/6/15 9:43
 */
public class DaoFreemarkerGenerator extends BaseFreemarkerGenerator {

    private static final String TEMPLATE_FILE = "dao.ftl";

    public static final DaoFreemarkerGenerator INSTANCE = new DaoFreemarkerGenerator();

    private DaoFreemarkerGenerator() {
    }

    @Override
    public Template getTemplate() throws IOException {
        return FreemarkerConfiguration.INSTANCE.getTemplate(TEMPLATE_FILE);
    }
}
