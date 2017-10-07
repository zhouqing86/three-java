package thread;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class Account {
    private String name;
    private int money;
    private ReentrantLock lock = new ReentrantLock();

    public Account(String name, int money) {
        this.name = name;
        this.money = money;
    }

    public void deposit(int amount) {
        try {
            lock.lock();
            this.money += amount;
        } finally {
            lock.unlock();
        }
    }

    public void draw(int amount) {
        try {
            lock.lock();
            this.money -= amount;
        } finally {
            lock.unlock();
        }
    }

    public int getMoney() {
        return this.money;
    }
}


public class ReentrantLockTest {

    public static void main(String[] args) {
        Account account = new Account("我的账户", 100);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Runnable depositThread = new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<10000; i++) {
                    account.deposit(10);
                }
            }
        };

        Runnable drawThread = new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<10000; i++) {
                    account.draw(10);
                }
            }
        };

        executorService.execute(depositThread);
        executorService.execute(drawThread);

        executorService.shutdown(); //只是不能再提交新任务，等待执行的任务不受影响

        try
        {
            // awaitTermination返回false即超时会继续循环，返回true即线程池中的线程执行完成主线程跳出循环往下执行，每隔10秒循环一次
            while (!executorService.awaitTermination(10, TimeUnit.SECONDS));
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        System.out.println("final account money: " + account.getMoney());
    }
}
