package com.bird.demo.infrastructure.rpc;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Map;
import java.util.Objects;

/**
 * @author master
 * @date 2020-04-22 17:31
 */
public class RemoteServiceImportRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware {

    private ResourceLoader resourceLoader;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        RemoteServiceBeanDefinitionScanner scanner = new RemoteServiceBeanDefinitionScanner(beanDefinitionRegistry);
        if (Objects.nonNull(resourceLoader)) {
            scanner.setResourceLoader(resourceLoader);
        }
        Map<String, Object> attributes = annotationMetadata.getAnnotationAttributes(EnableRemoteService.class.getName());
        String[] basePackages = (String[]) (attributes != null ? attributes.get("basePackages") : new String[0]);
        scanner.addIncludeFilter(new AnnotationTypeFilter(RemoteService.class));
        scanner.scan(basePackages);
    }
}
