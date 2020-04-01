package com.bird.core.consts;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 常量类
 *
 * @author youly
 * 2019/6/25 17:51
 */
public interface GlobalConst {

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
    boolean TRUE = true;
    boolean FALSE = false;
    int TRUE_INT = 1;
    int FALSE_INT = 0;
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
}
