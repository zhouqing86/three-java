package thread;

import java.util.concurrent.*;

public class ScheduledThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(3);
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
        ScheduledFuture<?> future = executor.scheduleAtFixedRate(() -> {
            System.out.println("Hello World");
            lock.countDown();
        }, 500, 100, TimeUnit.MILLISECONDS);

        lock.await(1000, TimeUnit.MILLISECONDS);
        System.out.println(lock.getCount());
        future.cancel(true);

        executor.shutdown();
        System.out.println("Shut down thread pool");
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
