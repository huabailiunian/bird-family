package com.bird.core;

import java.util.List;

/**
 * 类型转换器
 * S(sourceType)->R(resultType)
 *
 * @author youly
 * 2019/8/22 16:55
 */
public interface IConverter<S, R> {

    R converter(S source);

    List<R> converter(List<S> sources);
}
