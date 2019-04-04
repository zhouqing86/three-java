import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Static2Test {

    public static void main(String[] args) {
        staticFunction();
        System.out.println(Thread.currentThread().getContextClassLoader());
        ArrayList list = new ArrayList(20);
        LinkedList list2 = new LinkedList<String>();
        list2.add("ABC");
        System.out.println(list2);
        HashMap map = new HashMap();
        System.out.println(list.getClass().getGenericInterfaces().length);
        Object a = "abc";
        System.out.println(a.getClass());

    }

    static Static2Test st = new Static2Test();

    int a = 110;
    static int b = 112;

    static {
        System.out.println("1");
    }

    {
        System.out.println("2");
    }

    Static2Test() {
        System.out.println("3");
        System.out.println("a="+a+",b="+b);
    }

    public static void staticFunction() {
        System.out.println("4");
    }
}
