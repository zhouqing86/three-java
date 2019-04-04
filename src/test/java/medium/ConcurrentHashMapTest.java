package medium;


import map.ConcurrentHashMap;

public class ConcurrentHashMapTest {
//    static class Thread1 extends Thread {
//
//        private ConcurrentHashMap<Integer,String> map;
//
//        public Thread1(ConcurrentHashMap map) {
//            this.map = map;
//        }
//
//        @Override
//        public void run() {
//            for(;;) {
//                Integer integer = new Random().nextInt(10*1000);
//                map.put(integer, String.valueOf(integer));
//                if (integer > 9 * 1000) {
//                    System.out.println("Thread " + Thread.currentThread().getName() + " exit! Map size=" + map.size());
//                    break;
//                }
//                try {
//                    Thread.sleep(integer);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

    public static void main(String[] args) {
        System.out.println("aaaaa");
        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();
        map.put(1, "ABC");
        System.out.println(map.size());
//        for (int i=0; i<20; i++) {
//            new Thread1(map).start();
//        }
    }
}
