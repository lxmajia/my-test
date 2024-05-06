package cn.xwlin.distributeLock.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ZkCurator1 {
    private final static String zkaddr = "127.0.0.1:2181";

    public final static CuratorFramework client = CuratorFrameworkFactory
            .newClient(zkaddr, 1000 * 60, 1000 * 15, new RetryNTimes(10, 5000));

    private final static String wareRoot = "/ware/skuId/";
    private final static String wareRootNodePrefix = "lock_";
    private static CountDownLatch countDownLatch = null;
    private static String thisLockNode = null;
    private static String waitReleaseLockNode = null;
    private static ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 10, 2000, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

    public static void main(String[] args) throws Exception {
        client.start();
        tryLock("1");
    }

    private static String getLockNode(String skuId) {
        return wareRoot + skuId + "/" + wareRootNodePrefix;
    }

    private static String getLockPath(String skuId) {
        return wareRoot + skuId;
    }


    private static void tryLock(String skuId) throws Exception {
        if (acquire(skuId)) {
        } else {
            waitLock(waitReleaseLockNode);
        }
        business();
        release();
    }


    private static Boolean acquire(String skuId) throws Exception {
        String lockNode = getLockNode(skuId);
        String lockPath = getLockPath(skuId);
        String thisLock = client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE).forPath(lockNode, "lock".getBytes());
        thisLockNode = thisLock;
        System.out.println("thisLock:" + thisLock + ",lockPath:" + lockPath);
        // 得到节点，从新到旧
        List<String> zkNodes = client.getChildren().forPath(lockPath);
        Collections.sort(zkNodes);
        System.out.println(zkNodes);
        // 如果是第一个的话，就证明是第一次上锁
        if (thisLock.equals(lockPath + "/" + zkNodes.get(0))) {
            System.out.println("第一次就拿到锁：skuId:" + skuId);
            return true;
        } else {
            // 如果不是的话，得到当前创建的上一个
            System.out.println("没有拿到锁：skuId:" + skuId);
            for (int i = 0; i < zkNodes.size(); i++) {
                if (thisLock.equals(lockPath + "/" + zkNodes.get(i))) {
                    // 对之前一个进行上锁
                    waitReleaseLockNode = lockPath + "/" + zkNodes.get(i - 1);
                    System.out.println("设置监听节点：skuId:" + skuId + ",监听：" + waitReleaseLockNode);
                }
            }
            // 创建监听
        }
        return false;
    }

    public static void waitLock(String waitReleaseLockNode) throws Exception {
        Stat stat = client.checkExists().forPath(waitReleaseLockNode);
        if (stat != null) {
            countDownLatch = new CountDownLatch(1);
            // 监听
            NodeCache nodeCache = new NodeCache(client, waitReleaseLockNode);
            nodeCache.getListenable().addListener(() -> {
                ChildData childData = nodeCache.getCurrentData();
                if (childData == null) {
                    countDownLatch.countDown();
                }
            });
            nodeCache.start();
            Stat stat2 = client.checkExists().forPath(waitReleaseLockNode);

            if (stat2 != null) {
                countDownLatch.await();
            } else {
                nodeCache.close();
                countDownLatch = null;
            }
        }
    }

    private static void business() throws Exception {
        System.out.println(Thread.currentThread().getName() + "业务执行开始");
        Thread.sleep(10000);
        System.out.println(Thread.currentThread().getName() + "业务执行结束");
    }

    private static void release() throws Exception {
        Thread.sleep(10);
        client.close();
    }

}
