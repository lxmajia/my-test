package cn.xwlin.distributeLock.redisson;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class RedissonTest2 {
    private static String url = "redis://127.0.0.1:6379/0";
    private static RedissonClient redisson;

    public static void main(String[] args) throws Exception {
        Config config = new Config();
        config.useSingleServer().setAddress(url);
        redisson = Redisson.create(config);
        tryLock("mylockkey");
    }

    public static void tryLock(String lockKey) throws Exception {
        RLock lock = redisson.getLock(lockKey);
        lock.lock();
        try {
            business();
        } finally {
            lock.unlock();
            Thread.sleep(200);
            redisson.shutdown();
        }
    }

    private static void business() throws Exception {
        System.out.println(Thread.currentThread().getName() + "业务执行开始");
        Thread.sleep(10000);
        System.out.println(Thread.currentThread().getName() + "业务执行结束");
    }

    private static void release(String lockKey) throws Exception {
        redisson.getLock(lockKey).unlock();
        Thread.sleep(2000);
        redisson.shutdown();
    }
}
