package thread;

public class DeadlockTest {
    // Solution for Deadlock:
    //The deadlock occurred as the two threads were acquiring the locks in a different order. If we synchronize the thread in the same order then we can get rid of deadlock.

    public static Object lock1 = new Object();
    public static Object lock2 = new Object();

    public static void main(String[] args) {
        new Thread1().start();
        new Thread2().start();
    }

    private static class Thread1 extends Thread {
        @Override
        public void run() {
            synchronized (lock1) {
                System.out.println("Thread-1 acquired lock1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Thread-1 interrupted.");
                }
                System.out.println("Thread-1 waiting for lock2");
                synchronized (lock2) {
                    System.out.println("Thread-1 acquired lock2");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("Thread-1 interrupted.");
                    }
                }
                System.out.println("Thread-1 releases lock2");
            }
            System.out.println("Thread-1 releases lock1");
        }
    }

    private static class Thread2 extends Thread {
        @Override
        public void run() {
            synchronized (lock2) {
                System.out.println("Thread-2 acquired lock2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Thread-2 interrupted.");
                }
                System.out.println("Thread-2 waiting for lock1");
                synchronized (lock1) {
                    System.out.println("Thread-2 acquired lock1");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("Thread-2 interrupted.");
                    }
                }
                System.out.println("Thread-2 releases lock1");
            }
            System.out.println("Thread-2 releases lock2");
        }
    }
}
