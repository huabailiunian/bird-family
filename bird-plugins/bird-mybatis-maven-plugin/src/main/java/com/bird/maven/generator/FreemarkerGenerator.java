package com.bird.maven.generator;

import com.bird.core.generator.Generator;
import freemarker.template.Template;

import java.io.IOException;

/**
 * @author youly
 * 2019/1/18 17:29
 */
public interface FreemarkerGenerator extends Generator {

    Template getTemplate() throws IOException;
}
