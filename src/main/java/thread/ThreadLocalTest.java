package thread;

public class ThreadLocalTest {
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("helloworld!");

        Thread thread = new Thread(() -> {
            threadLocal.set("happy holiday!");
            System.out.println("Child thread: " + threadLocal.get());
        });

        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main thread: " + threadLocal.get());
    }
}
