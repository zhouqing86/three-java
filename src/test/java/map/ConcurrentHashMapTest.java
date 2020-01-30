package map;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConcurrentHashMapTest {

    @Test
    public void testNewCurrentHashMap() throws Exception {
        ConcurrentHashMap hashMap = new ConcurrentHashMap();
        hashMap.put("key", "value");
        System.out.println(Integer.MAX_VALUE);
        assertEquals(1, hashMap.size());
    }

    @Test
    public void testSpread() throws Exception {
        assertEquals(0xffff, ConcurrentHashMap.spread(0xffff));
        assertEquals(0x00000fff, 0x0fff0000 >> 16);
        assertEquals(0x0fff0fff, 0x0fff0000 ^ 0x00000fff);
        assertEquals(0x0fff0fff, ConcurrentHashMap.spread(0x0fff0000));
    }
}