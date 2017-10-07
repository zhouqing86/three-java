package thread;

import java.util.concurrent.CountDownLatch;

class Worker {
    private String name;
    private int operationTime;

    public Worker(String name, int operationTime) {
        this.name = name;
        this.operationTime = operationTime;
    }

    public void run() {
        System.out.println(name + " is running!");
        try {
            Thread.sleep(operationTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " finished!");
    }
}

class WorkerThread implements Runnable {
    private Worker worker;
    private CountDownLatch countDownLatch;

    public WorkerThread(Worker worker, CountDownLatch countDownLatch) {
        this.worker = worker;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        worker.run();
        countDownLatch.countDown();
    }
}

public class CountDownWatchTest {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Worker worker1 = new Worker("Thread 1", 1000);
        Worker worker2 = new Worker("Thread 2", 3000);

        new Thread(new WorkerThread(worker1, countDownLatch)).start();
        new Thread(new WorkerThread(worker2, countDownLatch)).start();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("All thread finished!");
    }

}
