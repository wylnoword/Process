package com.process.zookeeper.service.impl;

import com.process.zookeeper.service.ZkService;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class ZkServiceImpl implements ZkService {


    private static final String ROOT_PATH_LOCK = "rootlock";

    @Autowired
    private CuratorFramework zookeeper;

    @Override
    public void addRootPath(String name,CreateMode createMode) throws Exception {
        zookeeper.create().withMode(createMode).forPath("/"+name);
    }

    @Override
    public void addNodePath(String path, CreateMode createMode) throws Exception {
        zookeeper.create().withMode(createMode).forPath(path);
    }

    @Override
    public void deletePath(String path) throws Exception {
        zookeeper.delete().forPath(path);
    }

    @Override
    public void setData(String path,String data) throws Exception {
        zookeeper.setData().forPath(path,data.getBytes());
    }

    @Override
    public String getData(String path) throws Exception {
        byte[] bytes = zookeeper.getData().forPath(path);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    @Override
    public List<String> getChildPath(String path) throws Exception {
        return zookeeper.getChildren().forPath(path);
    }

    /**
     * curator的锁实现主要有下面几类:
     *
     * InterProcessMutex：分布式可重入排它锁,且公平的
     *    此锁可以重入，但是重入几次需要释放几次。
     * InterProcessSemaphoreMutex：分布式排它锁
     *  　是一种不可重入的互斥锁，也就意味着即使是同一个线程也无法在持有锁的情况下再次获得锁，
     *      所以需要注意，不可重入的锁很容易在一些情况导致死锁。
     * InterProcessReadWriteLock：分布式读写锁
     *    此锁可以重入，但是重入几次需要释放几次。
     * InterProcessMultiLock：将多个锁作为单个实体管理的容器
     * InterProcessSemaphoreV2 ：分布式的信号量
     * @return
     */
    @Override
    public boolean tryLock(String path) throws Exception {
        InterProcessMutex interProcessMutex = new InterProcessMutex(zookeeper, ROOT_PATH_LOCK);
        return interProcessMutex.acquire(10, TimeUnit.SECONDS);
    }

}
