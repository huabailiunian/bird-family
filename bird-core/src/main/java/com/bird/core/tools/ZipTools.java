package com.bird.core.tools;

import com.bird.core.consts.GlobalConst;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * 数据压缩、解压缩
 * @author youly
 * 2019/2/26 15:23
 */
public class ZipTools {


    public static byte[] gzip(byte[] data) throws IOException {
        if (ArrayUtils.isEmpty(data)) {
            return new byte[0];
        }
        ByteArrayOutputStream input = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(input);
        gzip.write(data);
        gzip.close();
        byte[] bytes = input.toByteArray();
        input.close();
        return bytes;
    }

    public static String gzipBase64(String data) throws IOException {
        if (StringUtils.isBlank(data)) {
            return GlobalConst.BLACK_STRING;
        }
        byte[] bytes = gzip(data.getBytes(GlobalConst.CHARSET_UTF8));
        return Base64.encodeBase64String(bytes);
    }


    public static String unGzip(byte[] data) throws IOException {
        if (ArrayUtils.isEmpty(data)) {
            return GlobalConst.BLACK_STRING;
        }
        int off = 0;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream input = new ByteArrayInputStream(data);
        GZIPInputStream gzip = new GZIPInputStream(input);

        byte[] buff = new byte[1024];
        int len;
        while ((len = gzip.read(buff)) != -1) {
            out.write(buff, off, len);
        }
        gzip.close();
        input.close();
        String result = out.toString(GlobalConst.CHARSET_UTF8.name());
        out.close();
        return result;
    }

    public static String unGzipBase64(String data) throws IOException {
        if (StringUtils.isBlank(data)) {
            return "";
        }
        return unGzip(Base64.decodeBase64(data));
    }
}
