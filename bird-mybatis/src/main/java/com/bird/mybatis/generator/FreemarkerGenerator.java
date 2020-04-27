package com.bird.mybatis.generator;

import freemarker.template.Template;

import java.io.IOException;

/**
 * @author youly
 * 2019/1/18 17:29
 */
public interface FreemarkerGenerator {

    /**
     * 获取 freemarker 模板
     *
     * @return 模板
     * @throws IOException
     */
    Template getTemplate() throws IOException;
}
