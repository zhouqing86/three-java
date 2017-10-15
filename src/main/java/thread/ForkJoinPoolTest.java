package thread;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

class MyTask extends RecursiveTask<Integer> {
    int i;

    public MyTask(int i) {
        this.i = i;
    }

    @Override
    protected Integer compute() {
        if (i >= 100) {
            return i * i;
        }

        MyTask newTask2 = new MyTask(i + 1);
        newTask2.fork();

        return i*i + newTask2.join();

    }

}

public class ForkJoinPoolTest {
    public static void main(String[] args) {
        MyTask mt = new MyTask(1);

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        Future<Integer> result = forkJoinPool.submit(mt);

        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        forkJoinPool.shutdown();
    }
}