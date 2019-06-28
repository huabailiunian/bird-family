package com.bird.maven.generator;

import freemarker.template.Template;

import java.io.IOException;

/**
 * @author youly
 * @version 1.0
 * 2018/6/15 9:47
 */
public class EntityFreemarkerGenerator extends BaseFreemarkerGenerator {

    private static final String TEMPLATE_FILE = "object.ftl";

    public static final EntityFreemarkerGenerator INSTANCE = new EntityFreemarkerGenerator();

    private EntityFreemarkerGenerator() {
    }

    @Override
    public Template getTemplate() throws IOException {
        return FreemarkerConfiguration.INSTANCE.getTemplate(TEMPLATE_FILE);
    }
}
