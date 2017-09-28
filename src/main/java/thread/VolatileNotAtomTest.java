package thread;

public class VolatileNotAtomTest {


        public volatile int inc = 0;

        public void increase() {
            inc++;
        }

        public static void main(String[] args) {

            final VolatileNotAtomTest test = new VolatileNotAtomTest();

            for(int i=0;i<10;i++){
                new Thread(){
                    public void run() {
                        for(int j=0;j<1000;j++)
                            test.increase();
                    };
                }.start();
            }

            while(Thread.activeCount()>2) {
                System.out.println("Thread.activeCount()" + Thread.activeCount());
                Thread.yield();
            }
            System.out.println(test.inc); //这个结果可能不是10000，因为increase方法的inc++不是原子操作。
        }
}
