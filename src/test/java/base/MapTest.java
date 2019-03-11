package base;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class MapTest {
    @Test
    public void testMapEntryComparator() throws Exception {
        HashMap<String, String> map = new HashMap<>();
        map.put("c", "Hello");
        map.put("a", "World");
        Comparator c = Map.Entry.comparingByKey();
        Set<Map.Entry<String,String>> set = map.entrySet();
        List<Map.Entry<String,String>> list = new ArrayList(set);
        Collections.sort(list, c);
        assertEquals("a", list.get(0).getKey());
        assertEquals("c", list.get(1).getKey());

        Comparator c2 = Map.Entry.comparingByValue();
        Collections.sort(list, c2);
        assertEquals("Hello", list.get(0).getValue());
        assertEquals("World", list.get(1).getValue());
    }

    @Test
    public void testTreeMap() throws Exception {
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(3, "abc");
        treeMap.put(1, "def");
        treeMap.put(2, "adc");

        assertEquals(new Integer(1), treeMap.firstKey());
        for (Map.Entry entry : treeMap.entrySet()) {
            System.out.println(entry.getKey());
        }
    }
}
