package thread;


/**
 * 并发编程的一个例子，用来说明可见性，number=42和ready=true执行的顺序并不是一定的，有可能编译器、处理器以及运行时做了一些调整导致ready = true;先执行
 * JRE判断程序是否执行结束的标准是所有的前台执线程行完毕了，而不管后台线程的状态，因此，在使用后台县城时候一定要注意这个问题。
 * 正常构建的线程都是前台线程，可以在线程未开始前调用Thread类的setDaemon(true)方法将线程改变为后台守护线程。
 * Java的main线程不能是守护线程，main线程是由java虚拟机在启动的时候创建的。main方法开始执行的时候，主线程已经创建好并在运行了。对于运行中的线程，调用Thread.setDaemon()会抛出异常
 * 当所有的非守护线程结束时，程序也就终止了，同时会杀死进程中的所有守护线程。反过来说，只要任何非守护线程还在运行，程序就不会终止。
 * 如果用户线程已经全部退出运行了，只剩下守护线程存在了，虚拟机也就退出了。因为没有了被守护者，守护线程也就没有工作可做了，也就没有继续运行程序的必要了。
 * 守护线程应该永远不去访问固有资源，如文件、数据库，因为它会在任何时候甚至在一个操作的中间发生中断。
 *
 * Java守护进程启动方式：nohup $JAVA_HOME/bin/java -server $JAVA_OPTS -classpath $CLASS_PATH $MAIN_CLASS >> $LOG_FILE 2>&1 & echo $! > $PID_FILE
 */
public class NoVisibility {

    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        public void run() {
            while(!ready) {
                Thread.yield();
            }
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        Thread t = new ReaderThread();
//        t.setDaemon(true);
        t.start();
        number = 42;
        ready = true;
//        try {
//            t.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("Parent thread complete");
    }
}
