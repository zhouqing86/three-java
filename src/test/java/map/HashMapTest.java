package map;

import org.junit.Test;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class HashMapTest {
    @Test
    public void testNewHashMap() throws Exception {
        HashMap hashMap = new HashMap();
        hashMap.put("key", "value");
        assertEquals(1, hashMap.size());
    }

    @Test
    public void testEmptyHashMap() throws Exception {
        HashMap hashMap = new HashMap();
        assertEquals(0, hashMap.size());
        assertEquals(0.75, hashMap.loadFactor, 0.000001);
        assertEquals(0, hashMap.threshold);
        assertEquals(16, hashMap.capacity());
        assertNull(hashMap.table);
        hashMap.put(1,1);
        assertEquals(1, hashMap.size());
        assertEquals(16, hashMap.table.length);


        hashMap = new HashMap(1000);
        assertEquals(0, hashMap.size());
        assertEquals(0.75, hashMap.loadFactor, 0.000001);
        assertEquals(1024, hashMap.capacity());
        assertEquals(1024, hashMap.threshold);
        hashMap.put(1,1);
        assertEquals(1, hashMap.size());
        assertEquals(1024*0.75, hashMap.threshold, 0.000001);
        assertEquals(1024, hashMap.capacity());
        assertEquals(1024, hashMap.table.length);

        hashMap = new HashMap(1000, 0.8f);
        assertEquals(0, hashMap.size());
        assertEquals(0.8, hashMap.loadFactor, 0.000001);
        assertEquals(1024, hashMap.capacity());
        assertEquals(1024, hashMap.threshold);
        hashMap.put(1,1);
        assertEquals(1, hashMap.size());
        assertEquals(Math.floor(1024*0.8), hashMap.threshold, 0.000001);
        assertEquals(1024, hashMap.capacity());
        assertEquals(1024, hashMap.table.length);
    }

    @Test
    public void testMixedHashMap() throws Exception {
        HashMap map = new HashMap();
        map.put("a", 1);
        map.put(1, "a");
        map.put(0, true);
        map.put(true, 1.2);
        map.put(false, "b");
        assertEquals(5, map.size());

        // String and Boolean actually not working
        map = new HashMap<String, Boolean>();
        map.put(1,"2");
        assertEquals(1, map.size());
        assertEquals("2", map.get(1));
    }

    @Test
    public void testIterateMapKeys() throws Exception {
        HashMap<Integer, String> idNameMap = new HashMap<>();
        idNameMap.put(1, "Zhang San");
        idNameMap.put(2, "Li Si");

        for (Integer key : idNameMap.keySet()) {
            assertTrue(key == 1 || key == 2);
        }

        for (Map.Entry<Integer,String> entry : idNameMap.entrySet()) {
            assertTrue(entry.getKey() == 1 || entry.getKey() == 2);
        }


        for (String value : idNameMap.values()) {
            assertTrue(value.equals("Zhang San") || value.equals("Li Si"));
        }

        Iterator<Integer> keyIterator = idNameMap.keySet().iterator();
        while (keyIterator.hasNext()) {
            Integer key = (Integer) keyIterator.next();
            assertTrue(key == 1 || key == 2);
        }
    }

    @Test
    public void testMapResize() throws Exception {
        HashMap map = new HashMap(2);
        assertEquals(0, map.size());
        assertEquals(0.75, map.loadFactor, 0.000001);
        assertEquals(2, map.threshold);
        map.put(1, 1);
        assertEquals(1, map.threshold);
        assertEquals(2, map.table.length);
        map.put(2, 2);
        assertEquals(3, map.threshold);
        assertEquals(4, map.table.length);
        map.put(3, 3);
        map.put(4, 4);
        assertEquals(6, map.threshold);
        assertEquals(8, map.table.length);
    }

    @Test
    public void testAccurateMap() throws Exception {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        assertEquals(1, map.size());
    }

    @Test
    public void testTableSizeFor() throws Exception {
        assertEquals(32, tableSizeFor(17));
    }

    @Test
    public void testHashmapIterator() throws Exception {
        HashMap hashMap = new HashMap();
        hashMap.put("key", "value");
        hashMap.put("key2", "value2");


        Set<String> keySet = hashMap.keySet();
    }

    @Test
    public void testNumberSup() throws Exception {
        assertEquals(2, 1 << 1);
        assertEquals(Math.pow(2,30), 1 << 30, 0.00001);
    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        System.out.println(Integer.toBinaryString(n >>> 1));
        n |= n >>> 1;
        System.out.println(Integer.toBinaryString(n >>> 2));
        n |= n >>> 2;
        System.out.println(Integer.toBinaryString(n >>> 4));
        n |= n >>> 4;
        System.out.println(Integer.toBinaryString(n >>> 8));
        n |= n >>> 8;
        System.out.println(Integer.toBinaryString(n >>> 16));
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= 1 << 30 ) ? 1 << 30 : n + 1;
    }
}
