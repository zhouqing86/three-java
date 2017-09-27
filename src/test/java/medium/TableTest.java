package medium;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TableTest {
    @Test
    public void testInitialTable() throws Exception {
        Table<String, Integer, Boolean> people = HashBasedTable.create();
        people.put("Wendll", 32, true);
        people.put("Wendll", 18, true);
        people.put("mozhong", 18, true);
        assertEquals(3, people.size());
        assertTrue(people.row("Wendll").containsKey(32));
        assertTrue(people.row("Wendll").containsKey(18));
    }
}
