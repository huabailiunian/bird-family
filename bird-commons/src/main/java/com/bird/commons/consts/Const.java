package com.bird.commons.consts;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 常量
 *
 * @author master
 * @date 2020-04-23 14:07
 */
public class Const {

    /**
     * 字符编码
     */
    public static final Charset CHARSET_UTF8 = StandardCharsets.UTF_8;
    public static final Charset CHARSET_GBK = Charset.forName("GBK");

    /**
     * 时间格式
     */
    public static final String FORMAT_DATE = "yyyy-MM-dd";
    public static final String FORMAT_TIME = "HH:mm:ss";
    public static final String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_TIMESTAMP = "yyyyMMddHHmmssSSS";

    public static final String NEW_LINE = "\n";
    public static final String EMPTY_STRING = "";
    public static final String UNDERLINE = "_";
    public static final String DEFAULT_SEPARATOR = ",";
    public static final String UNIX_PATH_SEPARATOR = "/";
    public static final String WINDOWS_PATH_SEPARATOR = "\\";

    public static final int ZERO = 0;
    public static final int ONE = 1;

}
