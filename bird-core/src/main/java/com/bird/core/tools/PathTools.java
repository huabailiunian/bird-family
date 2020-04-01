package com.bird.core.tools;

import com.bird.core.consts.GlobalConst;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;

/**
 * @author youly
 * 2019/10/21 14:41
 */
public class PathTools {

    public static String getPath(String... paths) {
        return StringUtils.collectionToDelimitedString(Arrays.asList(paths), GlobalConst.PATH_SEPARATOR_UNIX);
    }

    public static String getZkPath(String... paths) {
        String temp = getPath(paths);
        if (!StringUtils.startsWithIgnoreCase(temp, GlobalConst.PATH_SEPARATOR_UNIX)) {
            temp = GlobalConst.PATH_SEPARATOR_UNIX + temp;
        }
        if (StringUtils.endsWithIgnoreCase(temp, GlobalConst.PATH_SEPARATOR_UNIX)) {
            return temp.substring(GlobalConst.NUM_ZERO, temp.length() - GlobalConst.NUM_ONE);
        } else {
            return temp;
        }
    }

    public static String verifyPath(String path) {
        if (StringUtils.hasText(path)) {
            return path.replace(GlobalConst.PATH_SEPARATOR_WINDOWS, GlobalConst.PATH_SEPARATOR_UNIX);
        } else {
            return "";
        }
    }

    public static String getTempPath() {
        return verifyPath(SystemUtils.JAVA_IO_TMPDIR);
    }
}
