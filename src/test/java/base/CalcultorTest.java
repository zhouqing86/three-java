package base;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CalcultorTest {
    @Test
    public void testAdd() throws Exception {
        assertEquals(3, Calcultor.add(1,2));
    }

    @Test
    public void testSub() throws Exception {
        assertEquals(5, Calcultor.sub(10, 5));
    }
}
