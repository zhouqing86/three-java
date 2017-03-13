package rxjava;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

import java.util.Arrays;
import java.util.List;

public class HelloFlowable {
    public static void main(String[] args) {
        Flowable.range(1, 10)
                .observeOn(Schedulers.computation())
                .map(v -> v * v)
                .blockingSubscribe(System.out::println); //the lambda v -> v * v doesn't run in parallel for this flow; it receives the values 1 to 10 on the same computation thread one after the other.

        Flowable.range(1, 10)
                .flatMap(v ->
                        Flowable.just(v)
                                .subscribeOn(Schedulers.computation())
                                .map(w -> w * w)
                )
                .blockingSubscribe(System.out::println);


        //Starting from 2.0.5, there is an experimental operator parallel() and type ParallelFlowable that helps achieve the same parallel processing pattern
        Flowable.range(1, 10)
                .parallel()
                .runOn(Schedulers.computation())
                .map(v -> v * v)
                .sequential()
                .blockingSubscribe(System.out::println);


        Flowable.fromCallable(() -> {
            Thread.sleep(1000); //  imitate expensive computation
            return "Done";
        })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .blockingSubscribe(System.out::println, Throwable::printStackTrace);


        List<String> ls = Flowable.fromCallable(() -> {
            Thread.sleep(1000);
            return Arrays.asList("Hello", "Helle", "HOOO");
        })
                .blockingSingle();
        System.out.println(ls);

    }
}
