package com.bird.maven;

import com.bird.maven.service.DefaultService;
import com.bird.maven.service.GenService;
import com.bird.mybatis.model.Config;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * @author youly
 * @version 1.0
 * @goal mybatis
 * @phase generate-sources
 */
public class MybatisMojo extends AbstractMojo {

    /**
     * root path
     *
     * @parameter default-value="${project.basedir}/src/main/java" property="sourcePath"
     * @required
     */
    private String sourcePath;

    /**
     * mapper save root path
     *
     * @parameter default-value="${project.basedir}/src/main/resources" property="resourcesPath"
     * @required
     */
    private String resourcesPath;

    /**
     * source path
     *
     * @parameter default-value="${project.basedir}/src/main/resources" property="filePath"
     * @required
     */
    private String filePath;

    /**
     * class save base path
     *
     * @parameter default-value="com.auto.dal" property="basePackage"
     * @required
     */
    private String basePackage;

    /**
     * mapper save base path
     *
     * @parameter default-value="mybatis-mapper" property="mapperLocation"
     * @required
     */
    private String mapperLocation;

    /**
     * camel regex
     *
     * @parameter default-value="_" property="regex"
     * @required
     */
    private String regex;

    /**
     * @parameter default-value="true" property="autoDao"
     * @required
     */
    private Boolean autoDao;

    /**
     * @parameter default-value="true" property="autoMapper"
     * @required
     */
    private Boolean autoMapper;

    /**
     * @parameter default-value="false" property="autoSQL"
     * @required
     */
    private Boolean autoSQL;

    /**
     * @parameter default-value="Dao" property="daoSuffix"
     * @required
     */
    private String daoSuffix;

    /**
     * @parameter default-value="" property="entitySuffix"
     */
    private String entitySuffix;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        Config config = new Config();
        config.setSourcePath(this.sourcePath);
        config.setResourcesPath(this.resourcesPath);
        config.setBasePackage(this.basePackage);
        config.setMapperLocation(this.mapperLocation);
        config.setRegex(this.regex);
        config.setDaoSuffix(this.daoSuffix);
        config.setEntitySuffix(this.entitySuffix == null ? "" : this.entitySuffix);
        config.setAutoDao(this.autoDao);
        config.setAutoMapper(this.autoMapper);
        config.setAutoSQL(this.autoSQL);
        config.setAutoEntity(true);
        GenService service = new DefaultService();
        service.setLogger(this.getLog());
        service.execution(config, this.filePath);
    }
}
