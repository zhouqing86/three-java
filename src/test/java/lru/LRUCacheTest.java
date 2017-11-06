package lru;

import org.junit.Test;

import static org.junit.Assert.*;

public class LRUCacheTest {
    @Test
    public void testLRUCache() throws Exception {
        LRUCache<Integer, String> cache = new LRUCache<>(2);
        cache.put(1, "1");
        cache.put(2, "2");
        cache.put(3, "3");
        assertEquals(2, cache.size());
        assertNull(cache.get(1));
    }
}