package cn.xwlin.thread;

import java.util.concurrent.ThreadFactory;

/**
 * @author MaJiaXueYuan.lx
 * @ClassName MyThreadFactory.java
 * @createTime 2023-3-6-0006
 */
public class MyThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setName("My_THREAD");
        return thread;
    }
}
