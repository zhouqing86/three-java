package medium;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BiMapTest {

    @Test
    public void testBiMap() throws Exception {
        BiMap<String, Integer> biMap = HashBiMap.create();
        biMap.put("A", 1);
        biMap.put("B", 2);
        BiMap<Integer, String> inversedBiMap = biMap.inverse();
        assertEquals("A", inversedBiMap.get(1));
        assertEquals("B", inversedBiMap.get(2));
    }
}
