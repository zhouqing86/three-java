package map;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class HashMapTest {

    @Test
    public void testTableSizeFor() throws Exception {
        HashMap hashMap = new HashMap();
        System.out.println(hashMap.size());
//        System.out.println(tableSizeFor(17));
//        assertEquals(32, tableSizeFor(17));
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
