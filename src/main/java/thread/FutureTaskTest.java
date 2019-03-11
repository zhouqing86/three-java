package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {

    public static void main(String[] args) {
        Callable  c = new Callable() {
            @Override
            public Object call() throws Exception {
                Thread.sleep(6000);
                return 200;
            }
        };

        FutureTask ft = new FutureTask(c);

        ft.run();

        System.out.println("Waiting===");

        try {
            System.out.println(ft.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
