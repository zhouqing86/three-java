package base;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IntegerTest {
    @Test
    public void testIntegerEqual() throws Exception {

        Integer a = 127;
        Integer b = 127;
        assertTrue(a == b);


        a = 200;
        b = 200;
        assertFalse(a == b);
        assertTrue(a.equals(b));
    }
}
