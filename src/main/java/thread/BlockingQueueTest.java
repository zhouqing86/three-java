package thread;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

class Task {
    private String name;

    public String getName() {
        return name;
    }

    public Task(String name) {
        this.name = UUID.randomUUID().toString() + " " +  name;
    }

    public void run() {
        System.out.println("In task: " + name);
    }
}

class Consumer implements  Runnable {
    private BlockingQueue<Task> blockingQueue;
    private String name;

    public Consumer(BlockingQueue<Task> blockingQueue, String name) {
        this.blockingQueue = blockingQueue;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Task task = blockingQueue.take();
                System.out.println(name + " are running task " + task.getName());
                task.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

class Producer implements Runnable {
    private BlockingQueue<Task> blockingQueue;

    public Producer(BlockingQueue<Task> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while (true){
            try {
                Task task = new Task("product");
                blockingQueue.put(task);
                System.out.println("Producer are putting task " + task.getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


public class BlockingQueueTest {
    public static void main(String[] args) {

        BlockingQueue<Task> blockingQueue = new LinkedBlockingDeque<>(10);
        ExecutorService es = Executors.newFixedThreadPool(6);


        for(int i=0; i<3; i++) {
            es.execute(new Producer(blockingQueue));
        }

        for(int i=0; i<3; i++) {
            es.execute(new Consumer(blockingQueue, "Consumer" + i));
        }

    }
}
