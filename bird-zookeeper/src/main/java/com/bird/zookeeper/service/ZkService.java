package com.bird.zookeeper.service;

import com.bird.zookeeper.exception.NoNodeException;

import java.util.List;

/**
 * @author youly
 * 2019/10/21 14:57
 */
public interface ZkService {

    /**
     * 获取指定路径下的子路径
     *
     * @param path 路径
     * @return
     * @throws Exception
     */
    List<String> getChildren(String path) throws NoNodeException;

    /**
     * 创建永久路径
     * @param path
     * @return
     * @throws Exception
     */
    String create(String path) throws Exception;

    /**
     * 创建路径，父路径若不存在也会被创建，如果为临时路径，父路径为 Container
     * @param path
     * @param isTemp 是否为临时路径，临时路径在与 zk 端口连接后会被自动删除
     * @return
     * @throws Exception
     */
    String create(String path, boolean isTemp) throws Exception;

    /**
     *
     * @param path
     * @param recursive 是否创建父路径
     * @param isContainer 父路径是否为 Container，如果为 Container，当子路径均被删除时，Container 也会被删除
     * @param isTemp 临时路径在与 zk 端口连接后会被自动删除
     * @return
     * @throws Exception
     */
    String create(String path, boolean recursive, boolean isContainer, boolean isTemp) throws Exception;

    /**
     * 删除路径，默认不处理子路径
     * @param path
     * @throws Exception
     */
    void delete(String path) throws Exception;

    /**
     * 删除路径
     * @param path
     * @param recursive 是否删除子路径
     * @throws Exception
     */
    void delete(String path, boolean recursive) throws Exception;

    /**
     * 检查指定路径是否存在
     * @param path
     * @return true-存在，false-不存在
     * @throws Exception
     */
    boolean checkExist(String path) throws Exception;
}
