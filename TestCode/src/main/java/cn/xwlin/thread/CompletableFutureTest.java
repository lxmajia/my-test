package cn.xwlin.thread;
import java.util.concurrent.*;

/**
 * @author MaJiaXueYuan.lx
 * @ClassName MyPool.java
 * @createTime 2023-3-6-0006
 */
public class CompletableFutureTest {


    private static ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), new MyThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());

    public void submit(Runnable runnable) {
        pool.submit(runnable);
    }

    public static void main(String[] args) throws Exception {
        Callable<String> stringCallable = () -> {
            Thread.sleep(2000);
            return "Hello Callable";
        };

        String call = stringCallable.call();
        System.out.println("CALL:" + call);

        CompletableFuture<String> c1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ignored) {
            }
            return "CompletableFuture C1";
        });

        CompletableFuture<String> c2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ignored) {
            }
            return "CompletableFuture C2";
        });

        CompletableFuture<Integer> c3 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ignored) {
            }
            return 100;
        });

        c3.thenAccept((i) -> {
            System.out.println(i);
        });

        c2.thenAccept((s) -> {
            System.out.println(s);
        });

        CompletableFuture.allOf(c1, c2);

        System.out.println("C1:" + c1.get());
        System.out.println("C2:" + c2.get());

        System.out.println("Main Finish!");
    }

}
