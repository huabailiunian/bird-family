package com.bird.core.consts;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 常量类
 *
 * @author youly
 * 2019/6/25 17:51
 */
public interface BirdConst {

    /**
     * 字符编码
     */
    Charset CHARSET_UTF8 = StandardCharsets.UTF_8;
    Charset CHARSET_GBK = Charset.forName("GBK");
    /**
     * 定界、分隔等字符
     */
    String DELIMITER_DEFAULT = ",";
    String DELIMITER_UNDERLINE = "_";
    String NEW_LINE = "\n";
    String BLACK_STRING = "";
    String PATH_SEPARATOR_UNIX = "/";
    String PATH_SEPARATOR_WINDOWS = "\\";
    /**
     * 逻辑值
     */
    boolean BOOLEAN_TRUE = true;
    boolean BOOLEAN_FALSE = false;
    int BOOLEAN_TRUE_INT = 1;
    int BOOLEAN_FALSE_INT = 0;
    /**
     * 脱敏后字符
     */
    String GRAY_MASK_STR = "****";
    /**
     * 时间格式化
     */
    String FORMAT_DATE = "yyyy-MM-dd";
    String FORMAT_TIME = "HH:mm:ss";
    String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";
    String FORMAT_TIMESTAMP = "yyyyMMddHHmmssSSS";
    /**
     * 网络连接参数
     * 单位：毫秒
     */
    int CONNECT_TIME_OUT = 10000;
    int SOCKET_TIME_OUT = 20000;

    /**
     * 数字
     */
    int NUM_ZERO = 0;
    int NUM_ONE = 1;
    int NUM_TWO = 2;
    int NUM_THREE = 3;
    int NUM_FOUR = 4;
    int NUM_FIVE = 5;
    int NUM_SIX = 6;
    int NUM_SEVEN = 7;
    int NUM_EIGHT = 8;
    int NUM_NINE = 9;
}
