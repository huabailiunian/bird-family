package com.bird.commons.validator;

import com.bird.commons.tools.CollectionTools;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author master
 * @date 2020-04-24 10:57
 */
public class BeanValidator {

    private static final String ERROR_SPLIT = ";";

    private Validator validator;

    public BeanValidator(Validator validator) {
        this.validator = validator;
    }

    public <T> List<String> validate(T data) {
        if (Objects.isNull(data)) {
            return Collections.emptyList();
        }
        return validator.validate(data).stream().map(ConstraintViolation::getMessage).distinct().collect(Collectors.toList());
    }

    public <T> List<String> validate(List<T> list) {
        if (CollectionTools.isEmpty(list)) {
            return Collections.emptyList();
        }
        return list.stream().map(validator::validate).flatMap(Set::stream).map(ConstraintViolation::getMessage).distinct().collect(Collectors.toList());
    }
}
