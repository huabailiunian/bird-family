package com.bird.zookeeper.service;

import com.bird.core.consts.BirdConst;
import com.bird.zookeeper.exception.NoNodeException;
import com.bird.zookeeper.exception.ZKException;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CreateBuilder;
import org.apache.curator.framework.api.DeleteBuilder;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * @author youly
 * 2019/10/21 14:59
 */
public class ZkServiceImpl implements ZkService {

    private final CuratorFramework zkClient;

    public ZkServiceImpl(CuratorFramework zkClient) {
        this.zkClient = zkClient;
    }

    @Override
    public List<String> getChildren(String path) throws NoNodeException {
        try {
            return zkClient.getChildren().forPath(path);
        } catch (KeeperException.NoNodeException e) {
            throw new NoNodeException("不存在的路径", e);
        } catch (Exception e) {
            throw new ZKException("未处理异常", e);
        }
    }

    @Override
    public String create(String path) throws Exception {
        return create(path, BirdConst.BOOLEAN_FALSE);
    }

    @Override
    public String create(String path, boolean isTemp) throws Exception {
        if (isTemp) {
            return create(path, BirdConst.BOOLEAN_TRUE, BirdConst.BOOLEAN_TRUE, BirdConst.BOOLEAN_TRUE);
        } else {
            return create(path, BirdConst.BOOLEAN_TRUE, BirdConst.BOOLEAN_FALSE, BirdConst.BOOLEAN_FALSE);
        }
    }

    @Override
    public String create(String path, boolean recursive, boolean isContainer, boolean isTemp) throws Exception {
        CreateBuilder createBuilder = zkClient.create();
        if (recursive) {
            if (isContainer) {
                if (isTemp) {
                    return createBuilder.creatingParentContainersIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path);
                } else {
                    return createBuilder.creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path);
                }
            } else {
                if (isTemp) {
                    return createBuilder.creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path);
                } else {
                    return createBuilder.creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path);
                }
            }
        } else {
            if (isTemp) {
                return createBuilder.withMode(CreateMode.EPHEMERAL).forPath(path);
            } else {
                return createBuilder.withMode(CreateMode.PERSISTENT).forPath(path);
            }
        }
    }

    @Override
    public void delete(String path) throws Exception {
        delete(path, BirdConst.BOOLEAN_FALSE);
    }

    @Override
    public void delete(String path, boolean recursive) throws Exception {
        DeleteBuilder delete = zkClient.delete();
        if (recursive) {
            delete.deletingChildrenIfNeeded().forPath(path);
        } else {
            delete.forPath(path);
        }
    }

    @Override
    public boolean checkExist(String path) throws Exception {
        Stat stat = zkClient.checkExists().forPath(path);
        return stat != null;
    }
}
