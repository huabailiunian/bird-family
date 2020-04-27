package com.bird.mybatis.generator;

import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;

/**
 * @author youly
 * 2019/1/18 17:30
 */
public abstract class BaseFreemarkerGenerator implements FreemarkerGenerator {

    public String process(Object model) throws IOException {
        StringWriter writer = new StringWriter();
        try {
            this.getTemplate().process(model, writer);
        } catch (TemplateException e) {
            throw new IOException(e);
        }
        return writer.toString();
    }
}
