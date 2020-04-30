package com.bird.commons.tools;

import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author youly
 * 2019/4/25 11:08
 */
public class StringTools {
    private static final String HEX16_PREFIX = "\\u";
    private static final String SPLIT_REGEX = "\\\\u";
    private static final int HEX16_RADIX = 16;
    private static final String UNICODE_TEMP = "0000";

    public static String underlineToLowerCamel(String string) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, string);
    }

    public static String underlineToUpperCamel(String string) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, string);
    }

    public static String camelToLowerUnderline(String string) {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, string);
    }

    public static String camelToUpperUnderline(String string) {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, string);
    }

    public static String unicode(String str) {
        if (StringUtils.isBlank(str)) {
            return str;
        }
        StringBuilder builder = new StringBuilder();
        char[] chars = str.toCharArray();
        for (char c : chars) {
            String hexString = Integer.toHexString(c);
            builder.append(HEX16_PREFIX).append(UNICODE_TEMP.substring(hexString.length())).append(hexString);
        }
        return builder.toString();
    }

    public static String deUnicode(String str) {
        if (StringUtils.isBlank(str)) {
            return str;
        }
        String[] split = str.split(SPLIT_REGEX);
        return Arrays.stream(split).filter(StringUtils::isNoneBlank).map(s -> (char) Integer.parseInt(s, HEX16_RADIX)).map(String::valueOf).collect(Collectors.joining());
    }
}
