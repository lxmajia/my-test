package cn.xwlin.tio.thread;

/**
 * @author MaJiaXueYuan.lx
 * @ClassName ReportExecuteStatusThread.java
 * @createTime 2022-12-8-0008
 */
public class ReportExecuteStatusThread implements Runnable {
    @Override
    public void run() {
        System.out.println("#上报任务状态#" + System.currentTimeMillis());
    }
}
