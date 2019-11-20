package com.bird.codegen.engine;

import com.bird.codegen.exception.CodeGenException;
import freemarker.template.Template;

import java.io.StringWriter;

/**
 * @author youly
 * 2019/11/20 11:28
 */
public class CodeGenEngineImpl implements CodeGenEngine {

    @Override
    public String generator(Context context, String templateName) throws CodeGenException {
        try {
            Template template = FreemarkerConfiguration.INSTANCE.getTemplate(templateName);
            StringWriter writer = new StringWriter();
            template.process(context, writer);
            return writer.toString();
        } catch (Exception e) {
            throw new CodeGenException(e);
        }
    }
}
