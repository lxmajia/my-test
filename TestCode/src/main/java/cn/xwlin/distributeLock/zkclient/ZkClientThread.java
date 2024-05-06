package cn.xwlin.distributeLock.zkclient;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ZkClientThread {
    private final static String zkaddr = "127.0.0.1:2181";
    private final static String wareRoot = "/ware/skuId";

    private ZkClient zkClient;
    private CountDownLatch countDownLatch = null;
    private String lockNode = null;
    private String listenerLastLock = null;

    private static ThreadPoolExecutor pool = new ThreadPoolExecutor(20, 20, 2000, TimeUnit.SECONDS, new LinkedBlockingQueue<>());


    public static void main(String[] args) throws Exception {
        ZkClientThread zkClient1 = new ZkClientThread();
        zkClient1.setZkClient(new ZkClient(zkaddr));
        for (int i = 0; i < 20; i++) {
            pool.execute(() -> {
                try {
                    zkClient1.tryLock("1");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public void tryLock(String skuId) throws Exception {
        if (acquire(skuId)) {
        } else {
            waitLock(listenerLastLock);
        }
        business();
        release();
    }


    public Boolean acquire(String skuId) throws Exception {
        String thisLock = this.zkClient.create(wareRoot + "/" + skuId, "lock".getBytes(), CreateMode.EPHEMERAL_SEQUENTIAL);
        this.lockNode = thisLock;

        System.out.println(thisLock);
        // 得到节点，从新到旧
        List<String> zkNodes = this.zkClient.getChildren(wareRoot);
        Collections.sort(zkNodes);

        System.out.println(zkNodes);
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
                    this.listenerLastLock = wareRoot + "/" + zkNodes.get(i - 1);
                    System.out.println("设置监听节点：skuId:" + skuId + ",监听：" + this.listenerLastLock);
                }
            }
            // 创建监听
        }
        return false;
    }

    public void waitLock(String listenerLastLock) throws Exception {
        boolean exists = this.zkClient.exists(listenerLastLock);
        if (exists) {
            this.countDownLatch = new CountDownLatch(1);
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
                this.countDownLatch.await();
            } else {
                this.countDownLatch = null;
            }
        }
    }

    public void business() throws Exception {
        System.out.println(Thread.currentThread().getName() + "业务执行开始");
        Thread.sleep(10000);
        System.out.println(Thread.currentThread().getName() + "业务执行结束");
    }

    public void release() throws Exception {
        System.out.println("删除节点：" + lockNode);
        zkClient.close();
    }


    public ZkClient getZkClient() {
        return zkClient;
    }

    public void setZkClient(ZkClient zkClient) {
        this.zkClient = zkClient;
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public String getLockNode() {
        return lockNode;
    }

    public void setLockNode(String lockNode) {
        this.lockNode = lockNode;
    }

    public String getListenerLastLock() {
        return listenerLastLock;
    }

    public void setListenerLastLock(String listenerLastLock) {
        this.listenerLastLock = listenerLastLock;
    }
}
