package medium;

import com.google.common.collect.*;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class MultimapTest {
    @Test
    public void testInitialListMultiMap() throws Exception {
        ListMultimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put("A", "Test1");
        multimap.put("A", "Test2");
        List<String> lst = multimap.get("A");
        assertEquals(2, lst.size());
    }

    @Test
    public void testInitialSetMultiMap() throws Exception {
       SetMultimap<String, String> multimap = HashMultimap.create();
        multimap.put("A", "Test1");
        multimap.put("A", "Test2");
        multimap.put("A", "Test2");
        Set<String> set = multimap.get("A");
        assertEquals(2, set.size());
    }
}
