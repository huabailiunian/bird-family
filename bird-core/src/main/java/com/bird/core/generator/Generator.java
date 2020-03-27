package com.bird.core.generator;

import java.io.IOException;

/**
 * @author youly
 * 2019/5/10 13:35
 */
public interface Generator {

    /**
     * 处理
     *
     * @param object
     * @return
     * @throws IOException
     */
    String process(Object object) throws IOException;
}
