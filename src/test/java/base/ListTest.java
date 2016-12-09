package base;

import junit.framework.Assert;
import model.Person;
import org.junit.Test;

import java.util.*;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

public class ListTest {
    @Test
    public void testCreateAList() throws Exception {
        List<String> lst = new ArrayList<String>();
        lst.add("a");
        lst.add("a");
        lst.add("a");
        assertEquals(3,lst.size());
    }

    @Test
    public void testCreateListFromArray() throws Exception {
        String [] array = {"abc","abc","bcd"};
        List<String> lst = Arrays.asList(array);
        assertThat(lst, hasItems("abc", "abc"));
    }

    //AbstractList will throw exception when add(index, element)
    @Test(expected = UnsupportedOperationException.class)
    public void testInsertElementIntoListWillThrowException() throws Exception {
        String [] array = {"a", "b", "c"};

        //Arrays.asList将返回一个java.util.Arrays$ArrayList对象
        List<String> lst = Arrays.asList(array);
        lst.add(1, "d");
        assertArrayEquals(new String[]{"a","d","b","c"}, lst.toArray());
    }
    //java.util.Arrays$ArrayList和java.util.ArrayList有区别吗？
    //不一样，前者只是为了序列化写的一个内部类，只有一个参数为数组的构造函数，且没有add方法，这表示它不能动态扩展内部存储的数组。


    @Test
    public void testCreateArrayListFromArray() throws Exception {
        String [] array = {"a", "b", "c"};
        ArrayList<String> lst = new ArrayList<String>(Arrays.asList(array));
        lst.add("d");
        assertEquals(4, lst.size());
    }

    @Test
    public void testArrayListContainSomeElement() throws Exception {
        String [] array = {"a", "b", "c"};
        assertTrue(Arrays.asList(array).contains("b"));
    }

    @Test
    public void testClearList() throws Exception {
        String [] array = {"a", "b", "c"};
        ArrayList<String> lst = new ArrayList<String>(Arrays.asList(array));
        lst.clear();
        assertEquals(0,lst.size());

    }

    //ArrayList implement its own add(index, element)
    @Test
    public void testInsertElementIntoArrayList() throws Exception {
        List<String> lst = new ArrayList<String>();
        lst.add("a");
        lst.add(0,"b");
        assertEquals("b",lst.get(0));
    }

    @Test
    public void testForOverList() throws Exception {
        List<String> lst = new ArrayList<String>();
        lst.add("a");
        lst.add("b");
        String [] resultArr = {"a","b"};
        int i = 0;
        for(String str : lst) {
            assertEquals(resultArr[i],str);
            i++;
        }
    }

    @Test
    public void testIteratorOverList() throws Exception {
        List<String> lst = new ArrayList<String>();
        lst.add("a");
        lst.add("b");
        String [] resultArr = {"a","b"};
        int i = 0;

        for(Iterator iter = lst.iterator(); iter.hasNext();){
            assertEquals(resultArr[i],iter.next());
            i++;
        }
    }

    @Test
    public void testInitialVector() throws Exception {
        Vector<String> vec = new Vector<>(10);
        assertEquals(10, vec.capacity());
        assertEquals(0,vec.size());
        vec.addAll(Arrays.asList(new String []{"a","b","c","d","e","f","g","h","i","j"}));
        vec.add("h");
        assertEquals(20, vec.capacity());
    }

    @Test
    public void testInitialLinkedList() throws Exception {
        LinkedList<String> lst = new LinkedList<>();
        lst.addFirst("a");
        lst.addFirst("b");
        assertEquals("b", lst.getFirst());
        assertEquals("a",lst.getLast());
    }

    @Test
    public void testSearchFromList() throws Exception {
        List lst = Arrays.asList(new Integer[]{3, 2, 1, 4, 1, 7});
        ArrayList<Integer> arrayList = new ArrayList<Integer>(lst);
        LinkedList<Integer> linkedList = new LinkedList<Integer>(lst);
        assertEquals(2, arrayList.indexOf(1));
        assertEquals(2, linkedList.indexOf(1));
        assertEquals(4, arrayList.lastIndexOf(1));
        assertEquals(4, linkedList.lastIndexOf(1));
    }

    @Test
    public void testReplaceAllInTheList() throws Exception {
        List lst = Arrays.asList(new Integer[]{3, 2, 1, 4, 1, 7});
        ArrayList<Integer> arrayList = new ArrayList<Integer>(lst);
        LinkedList<Integer> linkedList = new LinkedList<Integer>(lst);
        Collections.replaceAll(arrayList,1,5);
        Collections.replaceAll(linkedList,1,5);
        assertEquals(-1, arrayList.indexOf(1));
        assertEquals(-1, linkedList.indexOf(1));
    }

    @Test
    public void testJava8LambdaInitAList() throws Exception {
        List<Person> people = new ArrayList<>();
    }
}
