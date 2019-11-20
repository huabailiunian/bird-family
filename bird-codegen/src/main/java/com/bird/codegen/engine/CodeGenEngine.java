package com.bird.codegen.engine;

import com.bird.codegen.exception.CodeGenException;

/**
 * @author youly
 * 2019/11/20 11:25
 */
public interface CodeGenEngine {

    String DEFAULT_TEMPLATE_POJO = "java_pojo.ftl";
    String DEFAULT_TEMPLATE_DAO = "java_dao.ftl";

    String generator(Context context, String templateName) throws CodeGenException;

}
