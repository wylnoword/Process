package com.process.zookeeper.listener;

import lombok.Getter;
import lombok.Setter;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;

@Getter
@Setter
public class ZkDataListener extends TreeCache {

    /**
     * Create a TreeCache for the given client and path with default options.
     * <p/>
     * If the client is namespaced, all operations on the resulting TreeCache will be in terms of
     * the namespace, including all published events.  The given path is the root at which the
     * TreeCache will watch and explore.  If no node exists at the given path, the TreeCache will
     * be initially empty.
     *
     * @param client the client to use; may be namespaced
     * @param path   the path to the root node to watch/explore; this path need not actually exist on
     *               the server
     * @see #newBuilder(CuratorFramework, String)
     */
    public ZkDataListener(CuratorFramework client, String path) {
        super(client, path);
    }

    public void listenData() throws Exception {
        this.getListenable().addListener((a, event) -> {
            if (event.getType() == TreeCacheEvent.Type.NODE_UPDATED) {
                System.out.println("数据更新");
            } else {
                System.out.println(event.getType());
            }
        });
    }

    public void listenNodeStatus() throws Exception {
        this.getListenable().addListener((a, event) -> {
            switch (event.getType()){
                case INITIALIZED:
                    System.out.println("节点初始化");
                    break;
                case NODE_ADDED:
                    System.out.println("添加节点");
                    break;
                case NODE_REMOVED:
                    System.out.println("删除节点");
                    break;
                default:
                    System.out.println(event.getType());
                    System.out.println("other");
            }
        });
    }
}
