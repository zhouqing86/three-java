package thread;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;


/**
 *
 *
 放入数据：
 　　offer(anObject):表示如果可能的话,将anObject加到BlockingQueue里,即如果BlockingQueue可以容纳,
 　　则返回true,否则返回false.（本方法不阻塞当前执行方法的线程）
 　　offer(E o, long timeout, TimeUnit unit),可以设定等待的时间，如果在指定的时间内，还不能往队列中加入BlockingQueue，则返回失败。
 　　put(anObject):把anObject加到BlockingQueue里,如果BlockQueue没有空间,则调用此方法的线程被阻断,直到BlockingQueue里面有空间再继续.

 获取数据：
 　　poll(time):取走BlockingQueue里排在首位的对象,若不能立即取出,则可以等time参数规定的时间,
 　　　　取不到时返回null;
 　　poll(long timeout, TimeUnit unit)：从BlockingQueue取出一个队首的对象，如果在指定时间内，队列一旦有数据可取，则立即返回队列中的数据。否则知道时间超时还没有数据可取，返回失败。
 　　take():取走BlockingQueue里排在首位的对象,若BlockingQueue为空,阻断进入等待状态直到BlockingQueue有新的数据被加入;
 　　drainTo():一次性从BlockingQueue获取所有可用的数据对象（还可以指定获取数据的个数），
 　　　　通过该方法，可以提升获取数据效率；不需要多次分批加锁或释放锁。


 BlockingQueue家庭大致有哪些成员:
 ArrayBlockingQueue,在ArrayBlockingQueue内部，维护了一个定长数组，以便缓存队列中的数据对象，这是一个常用的阻塞队列，除了一个定长数组外，ArrayBlockingQueue内部还保存着两个整形变量，分别标识着队列的头部和尾部在数组中的位置。
 ArrayBlockingQueue在生产者放入数据和消费者获取数据，都是共用同一个锁对象，由此也意味着两者无法真正并行运行，这点尤其不同于LinkedBlockingQueue；按照实现原理来分析，ArrayBlockingQueue完全可以采用分离锁，从而实现生产者和消费者操作的完全并行运行。Doug Lea之所以没这样去做，也许是因为ArrayBlockingQueue的数据写入和获取操作已经足够轻巧，以至于引入独立的锁机制，除了给代码带来额外的复杂性外，其在性能上完全占不到任何便宜。
 作为开发者，我们需要注意的是，如果构造一个LinkedBlockingQueue对象，而没有指定其容量大小，LinkedBlockingQueue会默认一个类似无限大小的容量（Integer.MAX_VALUE），这样的话，如果生产者的速度一旦大于消费者的速度，也许还没有等到队列满阻塞产生，系统内存就有可能已被消耗殆尽了
 ArrayBlockingQueue和LinkedBlockingQueue是两个最普通也是最常用的阻塞队列，一般情况下，在处理多线程间的生产者消费者问题，使用这两个类足以。

 *
 */

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
