package com.bird.core.tools;

import java.util.Collection;
import java.util.Map;

/**
 * 集合工具类
 *
 * @author youly
 * 2018/12/13 20:20
 */
public abstract class CollectionTools {

    public static boolean isEmpty(Map map) {
        return map == null || map.isEmpty();
    }

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNotEmpty(Map map) {
        return !isEmpty(map);
    }

    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }
}
