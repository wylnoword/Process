package com.process.zookeeper.config;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Curator提供了三种Watcher(Cache)来监听结点的变化  只讨论非一次性监听器
 *
 *     Path Cache : 监控一个ZNode的子节点. 当一个子节点增加， 更新，删除时， Path Cache会改变它的状态， 会包含最新的子节点， 子节点的数据和状态
 *     Node Cache : 只是监听某一个特定的节点
 *     Tree Cache : 可以监控整个树上的所有节点，类似于PathCache和NodeCache的组合
 *
 */
@Configuration
public class ZkConfig {

    @Bean("zookeeper")
    CuratorFramework getZkClient() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("localhost:2181", retryPolicy);
        System.out.println(client.getState());
        client.start();
        setListenterThreeTwo(client);
        return client;
    }

    private void setListenterThreeTwo(CuratorFramework client) throws Exception{
        ExecutorService pool = Executors.newCachedThreadPool();
        //设置节点的cache
        final NodeCache nodeCache = new NodeCache(client, "/process/d1", false);
        nodeCache.getListenable().addListener(() -> {
            System.out.println("the test node is change and result is :");
            System.out.println("path : "+nodeCache.getCurrentData().getPath());
            System.out.println("data : "+new String(nodeCache.getCurrentData().getData()));
            System.out.println("stat : "+nodeCache.getCurrentData().getStat());
        });

        TreeCache treeCache = new TreeCache(client, "/process/dd");
        treeCache.getListenable().addListener((curatorFramework, treeCacheEvent) -> {
            switch (treeCacheEvent.getType()){
                case NODE_UPDATED:
                    System.out.println("数据更新");
                    break;
                case NODE_ADDED:
                    System.out.println("加节点");
                    break;
                case NODE_REMOVED:
                    System.out.println("节点移除");
                    break;
                default:
                    System.out.println("others");
            }
        }, pool);
        nodeCache.start();
        treeCache.start();
    }
}
