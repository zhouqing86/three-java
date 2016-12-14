package medium;


import org.junit.Test;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;

public class GuavaTest {
    @Test
    public void testOptional() throws Exception {
        Optional<Integer> optInt = Optional.of(5);
        assertEquals(true, optInt.isPresent());
        assertEquals(new Integer(5), optInt.get());
    }
}

