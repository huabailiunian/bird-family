package com.bird.redis.spring;

import com.bird.redis.repository.RedisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author youly
 * 2019/5/16 20:39
 */
public class RedisSourceScannerRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware {

    private static final Logger logger = LoggerFactory.getLogger(RedisSourceScannerRegistrar.class);

    private ResourceLoader resourceLoader;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(RedisSourceScan.class.getName()));
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry, Boolean.FALSE);
        if (Objects.nonNull(resourceLoader)) {
            scanner.setResourceLoader(resourceLoader);
        }
        List<String> packages = new ArrayList<>();
        if (Objects.nonNull(attributes)) {
            boolean scanRepository = attributes.getBoolean("scanRepository");
            if (scanRepository) {
                logger.info("Searching for repository annotated with @RedisRepository");
                scanner.addIncludeFilter(new AnnotationTypeFilter(RedisRepository.class));
            }
            boolean scanConsumer = attributes.getBoolean("scanConsumer");

            String[] values = attributes.getStringArray("value");
            for (String value : values) {
                if (StringUtils.hasText(value)) {
                    packages.add(value);
                }
            }
            String[] basePackages = attributes.getStringArray("basePackages");
            for (String pkg : basePackages) {
                if (StringUtils.hasText(pkg)) {
                    packages.add(pkg);
                }
            }
            Class<?>[] classes = attributes.getClassArray("basePackageClasses");
            for (Class<?> cls : classes) {
                packages.add(ClassUtils.getPackageName(cls));
            }
            if (packages.isEmpty()){
                packages.add(ClassUtils.getPackageName(importingClassMetadata.getClassName()));
            }
            if (logger.isDebugEnabled()) {
                logger.debug("Searching for redis source base package {}", packages);
            }
            scanner.scan(StringUtils.toStringArray(packages));
        }
    }
}
