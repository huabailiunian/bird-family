package com.bird.codegen.utils;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author youly
 * 2019/11/19 17:49
 */
public class NamingUtils {

    /**
     * 提取类名
     *
     * @param fullClassName 类全名
     * @return name
     */
    public static String extractSimpleName(final String fullClassName) {
        return ClassUtils.getShortClassName(fullClassName);
    }

    /**
     * 提取包名
     *
     * @param fullClassName 类全名
     * @return packageName
     */
    public static String extractPackageName(final String fullClassName) {
        return ClassUtils.getPackageName(fullClassName);
    }

    /**
     * 生成有效的类全名
     *
     * @param packageName 包名
     * @param className   名字
     * @return fullClassName
     */
    public static String createQualifiedName(String packageName, String className) {
        if (StringUtils.isNotBlank(packageName)) {
            return packageName + "." + className;
        } else {
            return className;
        }
    }
}
