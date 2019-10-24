package com.bird.core.tools;

import com.bird.core.consts.BirdConst;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * 文件读写工具类
 *
 * @author youly
 * 2019/5/10 10:32
 */
public class FileTools {

    public static void forceMkdir(File directory) throws IOException {
        if (directory.exists()) {
            if (!directory.isDirectory()) {
                String message = "File " + directory + " exists and is not a directory. Unable to create directory.";
                throw new IOException(message);
            }
        } else {
            if (!directory.mkdirs()) {
                // Double-check that some other thread or process hasn't made
                // the directory in the background
                if (!directory.isDirectory()) {
                    String message = "Unable to create directory " + directory;
                    throw new IOException(message);
                }
            }
        }
    }

    public static String readClassPathFile(String path) throws IOException {
        InputStream stream = ClassLoader.getSystemResourceAsStream(path);
        return readFile(stream);
    }

    public static String readFile(InputStream inputStream, Charset charset) throws IOException {
        if (inputStream == null) {
            return "";
        } else {
            StringBuilder builder = new StringBuilder();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charset);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            String tmp;
            while ((tmp = reader.readLine()) != null) {
                builder.append(tmp).append(BirdConst.NEW_LINE);
            }

            reader.close();
            inputStreamReader.close();
            inputStream.close();
            return builder.toString();
        }
    }

    public static String readFile(InputStream inputStream) throws IOException {
        return readFile(inputStream, BirdConst.CHARSET_UTF8);
    }

    public static String readFile(File file) throws IOException {
        if (!file.exists()) {
            throw new FileNotFoundException(file.getAbsolutePath());
        } else {
            return readFile(new FileInputStream(file));
        }
    }

    public static void writeFile(File target, String data, boolean append) throws IOException {
        FileOutputStream stream = new FileOutputStream(target, append);
        OutputStreamWriter writer = new OutputStreamWriter(stream, BirdConst.CHARSET_UTF8);
        writer.write(data);
        writer.flush();
        writer.close();
        stream.close();
    }

    public static void writeFile(String file, String data) throws IOException {
        writeFile(new File(file), data, BirdConst.BOOLEAN_FALSE);
    }

    /**
     * 文件复制
     *
     * @param source 源文件
     * @param target 目标文件
     */
    public static void zeroCopy(File source, File target) throws IOException {
        FileChannel input = new FileInputStream(source).getChannel();
        FileChannel output = new FileOutputStream(target).getChannel();
        input.transferTo(BirdConst.NUM_ZERO, input.size(), output);
        input.close();
        output.close();
    }
}
