package com.bird.demo.config;

import com.bird.commons.validator.BeanValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validator;

/**
 * @author master
 * @date 2020-04-24 11:14
 */
@Configuration
public class ValidatorConfig {

    @Bean
    public BeanValidator beanValidator(Validator validator) {
        return new BeanValidator(validator);
    }

}
