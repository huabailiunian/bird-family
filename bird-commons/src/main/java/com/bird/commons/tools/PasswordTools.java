package com.bird.commons.tools;

import com.bird.commons.constant.Const;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author master
 * @date 2020-05-15 13:51
 */
public class PasswordTools {

    public static String encrypt(String password) {
        password = StringUtils.isBlank(password) ? Const.EMPTY_STRING : password;
        String salt = DigestUtils.md5Hex(password);
        return DigestUtils.md5Hex(password.concat(salt));
    }
}
