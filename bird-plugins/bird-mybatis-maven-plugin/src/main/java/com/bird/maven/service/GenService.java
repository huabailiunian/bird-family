package com.bird.maven.service;

import com.bird.mybatis.model.Config;
import org.apache.maven.plugin.logging.Log;

/**
 * @author youly
 * 2019/1/18 16:20
 */
public interface GenService {

    void execution(Config config, String filePath);

    void setLogger(Log logger);
}
