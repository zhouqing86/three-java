package rxjava;

import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HelloReactive {
    public static void main(String[] args) {
        Observable.just("Hello Reactive").subscribe(System.out::println);
        List<String> words = Arrays.asList(
                "the",
                "quick",
                "brown",
                "fox",
                "jumped",
                "over",
                "the",
                "lazy",
                "dog"
        );

        Observable.just(words)
                .map((w) -> w.size())
                .subscribe(System.out::println);
        Observable.fromIterable(words)
                .subscribe(System.out::println);
        Observable.range(1, 5)
                .subscribe(System.out::println);

        Observable.fromIterable(words)
                .zipWith(Observable.range(1, Integer.MAX_VALUE),
                        (string, count) -> String.format("%2d. %s", count, string))
                .subscribe(System.out::println);

        Observable.fromIterable(words)
                .flatMap(word -> Observable.fromArray(word.split("")))
                .distinct()
                .sorted()
                .zipWith(Observable.range(1, Integer.MAX_VALUE),
                        (string, count) -> String.format("%2d. %s", count, string))
                .subscribe(System.out::println);

        Observable.just(words)
                .delay(100, TimeUnit.MILLISECONDS)
                .subscribe(System.out::println);
    }
}
