package thread;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 Copy-On-Write简称COW，是一种用于程序设计中的优化策略。其基本思路是，从一开始大家都在共享同一个内容，当某个人想要修改这个内容的时候，才会真正把内容Copy出去形成一个新的内容然后再改，这是一种延时懒惰策略。
 从JDK1.5开始Java并发包里提供了两个使用CopyOnWrite机制实现的并发容器,它们是CopyOnWriteArrayList和CopyOnWriteArraySet。CopyOnWrite容器非常有用，可以在非常多的并发场景中使用到。




 */
public class CopyOnWriteArrayListExample{

    public static void main(String args[]) {

        CopyOnWriteArrayList<String> threadSafeList = new CopyOnWriteArrayList<String>();
        threadSafeList.add("Java");
        threadSafeList.add("J2EE");
        threadSafeList.add("Collection");

        //add, remove operator is not supported by CopyOnWriteArrayList iterator
        Iterator<String> failSafeIterator = threadSafeList.iterator();
        while(failSafeIterator.hasNext()){
            System.out.printf("Read from CopyOnWriteArrayList : %s %n", failSafeIterator.next());
            failSafeIterator.remove(); //not supported in CopyOnWriteArrayList in Java
        }
    }
}
