package com.process.zookeeper.config;

import com.process.zookeeper.listener.ZkDataListener;
import lombok.Data;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Value;
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
@Data
public class ZkConfig {

    @Value("${zookeeper.host}")
    private String zkHost;

    @Value("${zookeeper.port}")
    private String zkPort;

    @Bean("zookeeper")
    CuratorFramework getZkClient() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(2000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(zkHost+":"+zkPort, retryPolicy);
        client.start();
        ZkDataListener zkDataListener = new ZkDataListener(client,"/");
        zkDataListener.listenData();
        zkDataListener.listenNodeStatus();
        zkDataListener.start();
        return client;
    }

}
