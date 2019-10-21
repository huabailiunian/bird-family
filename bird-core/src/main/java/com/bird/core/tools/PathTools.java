package com.bird.core.tools;

import com.bird.core.consts.BirdConst;
import org.springframework.util.StringUtils;

import java.util.Arrays;

/**
 * @author youly
 * 2019/10/21 14:41
 */
public class PathTools {

    public static String getPath(String... paths) {
        return StringUtils.collectionToDelimitedString(Arrays.asList(paths), BirdConst.PATH_SEPARATOR_UNIX);
    }

    public static String getZkPath(String... paths) {
        String temp = getPath(paths);
        if (!StringUtils.startsWithIgnoreCase(temp, BirdConst.PATH_SEPARATOR_UNIX)) {
            temp = BirdConst.PATH_SEPARATOR_UNIX + temp;
        }
        if (StringUtils.endsWithIgnoreCase(temp, BirdConst.PATH_SEPARATOR_UNIX)) {
            return temp.substring(BirdConst.NUM_ZERO, temp.length() - BirdConst.NUM_ONE);
        } else {
            return temp;
        }
    }
}
