package base;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
