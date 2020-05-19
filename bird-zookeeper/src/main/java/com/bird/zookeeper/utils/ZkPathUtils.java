package com.bird.zookeeper.utils;

import com.bird.commons.constant.Const;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author master
 * @date 2020-05-19 10:46
 */
public class ZkPathUtils {

    public static String getPath(String... paths) {
        if (ArrayUtils.isEmpty(paths)) {
            return Const.EMPTY_STRING;
        }
        return String.join(Const.UNIX_PATH_SEPARATOR, paths);
    }

    public static String cleanPath(String... paths) {
        String temp = getPath(paths);
        temp = verifyPath(temp);
        if (!StringUtils.startsWithIgnoreCase(temp, Const.UNIX_PATH_SEPARATOR)) {
            temp = Const.UNIX_PATH_SEPARATOR.concat(temp);
        }
        if (StringUtils.endsWithIgnoreCase(temp, Const.UNIX_PATH_SEPARATOR)) {
            return temp.substring(Const.ZERO, temp.length() - Const.ONE);
        } else {
            return temp;
        }
    }

    public static String verifyPath(String path) {
        if (StringUtils.isNotBlank(path)) {
            return path.replace(Const.WINDOWS_PATH_SEPARATOR, Const.UNIX_PATH_SEPARATOR);
        } else {
            return Const.EMPTY_STRING;
        }
    }
}
