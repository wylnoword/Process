package com.process.zookeeper.service;

import org.apache.zookeeper.CreateMode;

import java.util.List;

public interface ZkService {

    void addRootPath(String name,CreateMode createMode) throws Exception;

    void addNodePath(String path, CreateMode createMode) throws Exception;

    void deletePath(String path) throws Exception;

    void setData(String path,String data) throws Exception;

    String getData(String path) throws Exception;

    List<String> getChildPath(String path) throws Exception;

    boolean tryLock();
}
