package thread;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

class TestCallable implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " are running");
        Thread.sleep(10);
        return new Random().nextInt(100);
    }
}

public class MyCallable {
    public static void main(String[] args) {
        List<Future<Integer>> futureList = new LinkedList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i=0; i<1000; i++) {
            futureList.add(executorService.submit(new TestCallable()));
        }

        int sum = 0;
        for(Future<Integer> future: futureList) {
            try {
                sum += future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Use executorService! sum=" + sum);


        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(executorService);
        for(int i=0; i<1000; i++) {
            completionService.submit(new TestCallable());
        }

        sum = 0;
        try {
            for(int i=0; i<1000; i++) {
                Future<Integer> future = completionService.take();
                sum += future.get();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("main thread exit! sum=" + sum);

    }
}
