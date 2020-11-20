package com.process.zookeeper.service.impl;

import com.process.zookeeper.service.ZkService;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class ZkServiceImpl implements ZkService {

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

    @Override
    public boolean tryLock() {
        return false;
    }
}
