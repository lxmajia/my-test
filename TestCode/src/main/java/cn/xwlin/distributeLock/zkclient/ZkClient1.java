package cn.xwlin.distributeLock.zkclient;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.zookeeper.CreateMode;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ZkClient1 {
    private final static String zkaddr = "127.0.0.1:2181";

    private static ZkClient zkClient = new ZkClient(zkaddr);

    private final static String wareRoot = "/ware/skuId";
    private static CountDownLatch countDownLatch = null;
    private static String lockNode = null;
    private static String listenerLastLock = null;


    public static void main(String[] args) throws Exception {
        tryLock("1");
    }

    public static void tryLock(String skuId) throws Exception {
        if (acquire(skuId)) {
        } else {
            waitLock(listenerLastLock);
        }
        business();
        release();
    }


    public static Boolean acquire(String skuId) throws Exception {

        String thisLock = zkClient.createEphemeralSequential(wareRoot + "/", "");
        lockNode = thisLock;

        System.out.println(thisLock);
        // 得到节点，从新到旧
        List<String> zkNodes = zkClient.getChildren(wareRoot);
        Collections.sort(zkNodes);
        // 如果是第一个的话，就证明是第一次上锁
        if (thisLock.equals(wareRoot + "/" + zkNodes.get(0))) {
            System.out.println("第一次就拿到锁：skuId:" + skuId);
            return true;
        } else {
            // 如果不是的话，得到当前创建的上一个
            System.out.println("没有拿到锁：skuId:" + skuId);
            for (int i = 0; i < zkNodes.size(); i++) {
                if (thisLock.equals(wareRoot + "/" + zkNodes.get(i))) {
                    // 对之前一个进行上监听
                    listenerLastLock = wareRoot + "/" + zkNodes.get(i - 1);
                    System.out.println("设置监听节点：skuId:" + skuId + ",监听：" + listenerLastLock);
                }
            }
            // 创建监听
        }
        return false;
    }

    public static void waitLock(String listenerLastLock) throws Exception {
        boolean exists = zkClient.exists(listenerLastLock);
        if (exists) {
            countDownLatch = new CountDownLatch(1);
            zkClient.subscribeDataChanges(listenerLastLock, new IZkDataListener() {
                @Override
                public void handleDataChange(String s, Object o) throws Exception {
                }

                @Override
                public void handleDataDeleted(String s) throws Exception {
                    countDownLatch.countDown();
                }
            });
            boolean exists2 = zkClient.exists(listenerLastLock);
            if (exists2) {
                countDownLatch.await();
            } else {
                countDownLatch = null;
            }
        }
    }

    public static void business() throws Exception {
        System.out.println(Thread.currentThread().getName() + "业务执行开始");
        Thread.sleep(10000);
        System.out.println(Thread.currentThread().getName() + "业务执行结束");
    }

    public static void release() throws Exception {
        System.out.println("删除节点：" + lockNode);
        zkClient.close();
    }
}
