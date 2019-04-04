package singleton;

public class Singleton1 {
    private static Singleton1 instance = new Singleton1();

    private Singleton1(){
        System.out.println("init Singleton1");
    }

    public static Singleton1 getInstance() {
        System.out.println("getInstance()");
        return instance;
    }

    public static void main(String[] args) {

    }
}
