package com.bird.zookeeper.service;

import com.bird.core.consts.GlobalConst;
import com.bird.zookeeper.exception.NoNodeException;
import com.bird.zookeeper.exception.ZkException;
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
            throw new NoNodeException("路径不存在", e);
        } catch (Exception e) {
            throw new ZkException("未知异常", e);
        }
    }

    @Override
    public String create(String path) throws Exception {
        return create(path, GlobalConst.FALSE);
    }

    @Override
    public String create(String path, boolean isTemp) throws Exception {
        if (isTemp) {
            return create(path, GlobalConst.TRUE, GlobalConst.TRUE, GlobalConst.TRUE);
        } else {
            return create(path, GlobalConst.FALSE, GlobalConst.FALSE, GlobalConst.FALSE);
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
        delete(path, GlobalConst.FALSE);
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
