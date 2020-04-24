package com.bird.demo.rpc;

import java.lang.reflect.Type;

/**
 * @author master
 * @date 2020-04-23 09:51
 */
public interface ResultDecode {

    /**
     * 数据解码
     *
     * @param rs   原始数据
     * @param type 类型
     * @param <T>  泛型
     * @return 目标数据
     */
    <T> T decode(String rs, Type type);
}
