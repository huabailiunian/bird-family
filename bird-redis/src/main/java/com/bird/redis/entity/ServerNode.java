package com.bird.redis.entity;

/**
 * 服务器节点
 *
 * @author youly
 * 2019/3/21 16:52
 */
public class ServerNode {

    private String protocol;
    private String address;
    private String port;

    public ServerNode(String protocol, String address, String port) {
        this.protocol = protocol == null ? "redis" : protocol;
        this.address = address;
        this.port = port;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return protocol + "://" + address + ":" + port;
    }
}
