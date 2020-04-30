package com.bird.demo.infrastructure.validator;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * @author youly
 * 2019/7/16 09:50
 */
public class DataValidator {


    public static boolean weakVerifyMobile(String mobile) {
        if (StringUtils.isNotBlank(mobile)) {
            mobile = mobile.trim();
            if (checkMobilePrefix(mobile)) {
                return checkMobileLength(mobile);
            }
        }
        return false;
    }

    public static boolean checkMobileLength(String mobile) {
        int index = mobile.indexOf("1");
        return index >= 0 && mobile.substring(index).length() == 11;
    }

    public static boolean checkMobilePrefix(String mobile) {
        return mobile.startsWith("1") || mobile.startsWith("86") || mobile.startsWith("+86");
    }

    public static boolean weakVerifyIdNumber(String idNumber) {
        if (StringUtils.isNotBlank(idNumber)) {
            return checkIdNumberRegex(idNumber.trim());
        }
        return false;
    }

    private static boolean checkIdNumberRegex(String idNumber) {
        return Pattern.matches("^([0-9]{17}[0-9Xx]|[0-9]{15})$", idNumber);
    }
}
