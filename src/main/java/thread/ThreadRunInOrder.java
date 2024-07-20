package thread;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

class TextDownloader implements Runnable {

    @Override
    public void run() {
        // Download text from server
        System.out.println("I am downloading the file containing text to a directory");
        try {
            Thread.sleep(2000); // for simulating download wait time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class TextDownloader2 implements Supplier<String> {

    @Override
    public String get() {
        System.out.println("I am downloading the file containing text to a directory");
        try {
            Thread.sleep(2000); // for simulating download wait time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "pathToDownloadedFile";
    }
}

class TextProcessor implements Runnable {

    @Override
    public void run() {
        // Process downloaded text
        System.out.println("I am taking the text from the location and processing the text");
    }
}

class TextProcessor2 implements java.util.function.Consumer<String> {
    @Override
    public void accept(String s) {
        // Process downloaded text
        System.out.println("I am taking the text from the location and processing the text");
        System.out.println("Path: " + s);
    }
}

public class ThreadRunInOrder {
    public static void main(String[] args) {
        CompletableFuture
                .runAsync(new TextDownloader())
                .thenRunAsync(new TextProcessor())
        .join();

        System.out.println("=".repeat(100));

        CompletableFuture
                .supplyAsync(new TextDownloader2())
                .thenAccept(new TextProcessor2())
                .join();
    }
}
