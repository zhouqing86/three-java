package medium;

import com.google.common.util.concurrent.*;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class GuavaParallelProgrammingTest {
    @Test
    public void testListenableFuture() throws Exception {
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
        final ListenableFuture<Integer> listenableFuture = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("call execute..");
                TimeUnit.MILLISECONDS.sleep(100);
                return 70;
            }
        });

        MyFutureCallBack<Integer> callback = new MyFutureCallBack<>();

        Futures.addCallback(listenableFuture, callback);

        TimeUnit.MILLISECONDS.sleep(120);

        assertEquals(new Integer(70), callback.getResult());
    }

    @Test
    public void testRateLimiter() throws Exception {
        RateLimiter rateLimiter = RateLimiter.create(4.0);

    }
}

class MyFutureCallBack<Integer> implements FutureCallback<Integer> {
    private  Integer retNum;

    @Override
    public void onSuccess(Integer result) {
        this.retNum = result;
    }

    @Override
    public void onFailure(Throwable t) {
        t.printStackTrace();
    }

    public Integer getResult() {
        return retNum;
    }
}
