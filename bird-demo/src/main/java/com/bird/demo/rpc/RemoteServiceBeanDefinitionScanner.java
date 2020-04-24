package com.bird.demo.rpc;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

import java.util.Set;

/**
 * @author master
 * @date 2020-04-23 10:08
 */
public class RemoteServiceBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {

    public RemoteServiceBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        super(registry, Boolean.FALSE);
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> definitionHolders = super.doScan(basePackages);
        processBeanDefinition(definitionHolders);
        return definitionHolders;
    }

    private void processBeanDefinition(Set<BeanDefinitionHolder> definitionHolders) {
        for (BeanDefinitionHolder holder : definitionHolders) {
            GenericBeanDefinition beanDefinition = (GenericBeanDefinition) holder.getBeanDefinition();
            String beanClassName = beanDefinition.getBeanClassName();
            beanDefinition.setBeanClass(RemoteServiceFactoryBean.class);
            beanDefinition.getPropertyValues().add("type", beanClassName);
            beanDefinition.getPropertyValues().add("httpInvocationHandler", new RuntimeBeanReference("httpInvocationHandler"));
            beanDefinition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);

        }
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
    }
}
