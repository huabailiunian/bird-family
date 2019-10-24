package com.bird.core.tools;

import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * SFTP 工具
 *
 * @author youly
 * 2019/10/24 11:15
 */
public class SftpTools {

    private Logger logger = LoggerFactory.getLogger(SftpTools.class);

    public static final String CHANNEL_TYPE_SFTP = "sftp";

    /**
     * 主机IP
     */
    private String host;
    /**
     * 端口
     */
    private int port;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private Session getSession() throws JSchException {
        logger.debug("......开始连接远程SFTP服务器......");
        JSch jsch = new JSch();
        //取得对话
        Session session = jsch.getSession(username, host, port);
        session.setPassword(password);
        Properties sshConfig = new Properties();
        sshConfig.put("StrictHostKeyChecking", "no");
        session.setConfig(sshConfig);
        session.setTimeout(5000);
        //连接对话
        session.connect();
        logger.debug("......连接远程SFTP服务器完成......");
        return session;
    }

    private void close(Session session, ChannelSftp channel) {
        if (channel != null && channel.isConnected()) {
            channel.disconnect();
        }
        if (session != null && session.isConnected()) {
            session.disconnect();
        }
    }

    /**
     * 直接从流上传文件
     *
     * @param remotePath  服务器放置路径
     * @param fileName    文件名称
     * @param inputStream 文件流
     * @throws JSchException 连接失败
     * @throws SftpException SFTP通信异常
     */
    public void upload(String remotePath, String fileName, InputStream inputStream) throws JSchException, SftpException {
        //建立sftp连接
        Session session = this.getSession();
        ChannelSftp channel = (ChannelSftp) session.openChannel(CHANNEL_TYPE_SFTP);
        try {
            //打开SFTP通道
            channel.connect();
            //上传文件
            this.verifyPath(channel, remotePath);
            String dir = PathTools.verifyPath(remotePath);
            channel.cd(dir);
            channel.put(inputStream, fileName, ChannelSftp.RESUME);
        } finally {
            this.close(session, channel);
        }
    }

    /**
     * 下载文件（仅写入输出流）
     *
     * @param remotePath   远程文件路径
     * @param fileName     远程文件名
     * @param outputStream 输出流
     * @throws JSchException 连接失败
     * @throws SftpException SFTP通信异常
     */
    public void download(String remotePath, String fileName, OutputStream outputStream) throws JSchException, SftpException {
        //建立sftp连接
        Session session = this.getSession();
        ChannelSftp channel = (ChannelSftp) session.openChannel(CHANNEL_TYPE_SFTP);
        try {
            //打开SFTP通道
            channel.connect();
            // 下载文件
            String dir = PathTools.verifyPath(remotePath);
            channel.cd(dir);
            channel.get(fileName, outputStream);
        } finally {
            this.close(session, channel);
        }
    }

    /**
     * 检查远程目录是否存在, 不存在则创建
     *
     * @param channel ftp会话
     * @param path    远程目录
     */
    private void verifyPath(ChannelSftp channel, String path) {
        Path temp = Paths.get(path);
        String dir = PathTools.verifyPath(path);
        while (true) {
            try {
                channel.ls(dir);
                break;
            } catch (SftpException e) {
                try {
                    channel.mkdir(dir);
                    break;
                } catch (SftpException e1) {
                    verifyPath(channel, temp.getParent().toString());
                }
            }
        }
    }
}
