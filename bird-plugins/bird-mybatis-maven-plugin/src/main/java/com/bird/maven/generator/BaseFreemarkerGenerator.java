package com.bird.maven.generator;

import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;

/**
 * @author youly
 * 2019/1/18 17:30
 */
public abstract class BaseFreemarkerGenerator implements FreemarkerGenerator {

    @Override
    public String process(Object objectModule) throws IOException {
        StringWriter writer = new StringWriter();
        try {
            this.getTemplate().process(objectModule, writer);
        } catch (TemplateException e) {
            throw new IOException(e);
        }
        return writer.toString();
    }
}
