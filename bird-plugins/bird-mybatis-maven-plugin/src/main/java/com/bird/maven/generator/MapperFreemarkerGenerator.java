package com.bird.maven.generator;

import freemarker.template.Template;

import java.io.IOException;

/**
 * @author youly
 * @version 1.0
 * 2018/6/15 9:49
 */
public class MapperFreemarkerGenerator extends BaseFreemarkerGenerator {

    private static final String TEMPLATE_FILE = "mapper.ftl";

    public static final MapperFreemarkerGenerator INSTANCE = new MapperFreemarkerGenerator();

    private MapperFreemarkerGenerator() {
    }

    @Override
    public Template getTemplate() throws IOException {
        return FreemarkerConfiguration.INSTANCE.getTemplate(TEMPLATE_FILE);
    }
}
