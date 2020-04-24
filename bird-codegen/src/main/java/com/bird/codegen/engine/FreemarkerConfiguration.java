package com.bird.codegen.engine;

import com.bird.commons.constant.Const;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.IOException;

/**
 * @author youly
 * @version 1.0
 * 2018/6/15 9:38
 */
public class FreemarkerConfiguration {

    private static final String DEFAULT_TEMPLATE_PATH = "/templates/";

    public static final FreemarkerConfiguration INSTANCE = new FreemarkerConfiguration();

    private Configuration config;

    private FreemarkerConfiguration() {
        this.config = new Configuration(Configuration.VERSION_2_3_23);
        this.config.setTemplateLoader(new ClassTemplateLoader(FreemarkerConfiguration.class, DEFAULT_TEMPLATE_PATH));
        this.config.setDefaultEncoding(Const.CHARSET_UTF8.name());
    }

    public Template getTemplate(String name) throws IOException {
        return this.config.getTemplate(name);
    }

}
