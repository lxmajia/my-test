package cn.xwlin.timewheel;

import java.util.concurrent.ThreadFactory;

public class TimeWheelThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread();
        thread.setName("TimeWheel-Dispatch-Task");
        return null;
    }
}
